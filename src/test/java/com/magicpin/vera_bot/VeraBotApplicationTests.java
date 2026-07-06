package com.magicpin.vera_bot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
class VeraBotApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void healthEndpointShouldReturnOk() throws Exception {

        mockMvc.perform(get("/v1/healthz"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ok"));
    }

    @Test
    void metadataEndpointShouldReturnMetadata() throws Exception {

        mockMvc.perform(get("/v1/metadata"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.teamName").exists())
                .andExpect(jsonPath("$.model").exists())
                .andExpect(jsonPath("$.version").exists());
    }

    @Test
    void shouldSaveMerchantContext() throws Exception {

        String json = """
    {
      "scope":"merchant",
      "context_id":"merchant-001",
      "version":1,
      "payload":{
        "merchantId":"merchant-001"
      },
      "delivered_at":"2026-07-06T10:00:00Z"
    }
    """;

        mockMvc.perform(post("/v1/context")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accepted").value(true))
                .andExpect(jsonPath("$.ackId").exists());
    }

    @Test
    void shouldRejectDuplicateVersion() throws Exception {

        String json = """
    {
      "scope":"merchant",
      "context_id":"merchant-duplicate",
      "version":1,
      "payload":{
        "merchantId":"merchant-duplicate"
      },
      "delivered_at":"2026-07-06T10:00:00Z"
    }
    """;

        mockMvc.perform(post("/v1/context")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        mockMvc.perform(post("/v1/context")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accepted").value(true))
                .andExpect(jsonPath("$.ackId").value("duplicate"));
    }

    @Test
    void shouldRejectStaleVersion() throws Exception {

        String version2 = """
    {
      "scope":"merchant",
      "context_id":"merchant-stale",
      "version":2,
      "payload":{
        "merchantId":"merchant-stale"
      },
      "delivered_at":"2026-07-06T10:00:00Z"
    }
    """;

        String version1 = """
    {
      "scope":"merchant",
      "context_id":"merchant-stale",
      "version":1,
      "payload":{
        "merchantId":"merchant-stale"
      },
      "delivered_at":"2026-07-06T10:00:00Z"
    }
    """;

        mockMvc.perform(post("/v1/context")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(version2))
                .andExpect(status().isOk());

        mockMvc.perform(post("/v1/context")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(version1))
                .andExpect(status().isConflict());
    }

    @Test
    void shouldSendReply() throws Exception {

        String json = """
    {
      "conversation_id":"reply-send",
      "merchant_id":"m1",
      "customer_id":"c1",
      "from_role":"merchant",
      "message":"Tell me about Magicpin",
      "received_at":"2026-07-06T10:00:00Z",
      "turn_number":1
    }
    """;

        mockMvc.perform(post("/v1/reply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.action").value("send"))
                .andExpect(jsonPath("$.body").exists())
                .andExpect(jsonPath("$.cta").value("open_ended"));
    }

    @Test
    void shouldWaitWhenMerchantBusy() throws Exception {

        String json = """
    {
      "conversation_id":"reply-wait",
      "merchant_id":"m1",
      "customer_id":"c1",
      "from_role":"merchant",
      "message":"I am busy today. Contact me tomorrow.",
      "received_at":"2026-07-06T10:00:00Z",
      "turn_number":1
    }
    """;

        mockMvc.perform(post("/v1/reply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.action").value("wait"))
                .andExpect(jsonPath("$.waitSeconds").value(86400));
    }

    @Test
    void shouldEndConversation() throws Exception {

        String json = """
    {
      "conversation_id":"reply-end",
      "merchant_id":"m1",
      "customer_id":"c1",
      "from_role":"merchant",
      "message":"Stop messaging me",
      "received_at":"2026-07-06T10:00:00Z",
      "turn_number":1
    }
    """;

        mockMvc.perform(post("/v1/reply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.action").value("end"));
    }

    @Test
    void shouldDetectAutoReply() throws Exception {

        String json = """
    {
      "conversation_id":"auto-test",
      "merchant_id":"m1",
      "customer_id":"c1",
      "from_role":"merchant",
      "message":"Thank you for contacting us. Our team will respond shortly.",
      "received_at":"2026-07-06T10:00:00Z",
      "turn_number":1
    }
    """;

        mockMvc.perform(post("/v1/reply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.action").value("send"))
                .andExpect(jsonPath("$.cta").value("binary_yes_no"));
    }

    @Test
    void shouldHandleInterestedMerchant() throws Exception {

        String json = """
    {
      "conversation_id":"interest-test",
      "merchant_id":"m1",
      "customer_id":"c1",
      "from_role":"merchant",
      "message":"Yes, let's do it",
      "received_at":"2026-07-06T10:00:00Z",
      "turn_number":1
    }
    """;

        mockMvc.perform(post("/v1/reply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.action").value("send"))
                .andExpect(jsonPath("$.body").exists());
    }

    @Test
    void shouldHandleRepeatedAutoReply() throws Exception {

        String json = """
    {
      "conversation_id":"auto-flow",
      "merchant_id":"m1",
      "customer_id":"c1",
      "from_role":"merchant",
      "message":"Thank you for contacting us. Our team will respond shortly.",
      "received_at":"2026-07-06T10:00:00Z",
      "turn_number":1
    }
    """;

        mockMvc.perform(post("/v1/reply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(jsonPath("$.action").value("send"));

        mockMvc.perform(post("/v1/reply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(jsonPath("$.action").value("wait"));

        mockMvc.perform(post("/v1/reply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(jsonPath("$.action").value("end"));
    }
}