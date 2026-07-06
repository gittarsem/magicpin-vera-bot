package com.magicpin.vera_bot.exception;

import com.magicpin.vera_bot.dto.ContextResponse;
import com.magicpin.vera_bot.dto.VersionConflictResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StaleVersionException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public VersionConflictResponse staleVersion() {

        return new VersionConflictResponse(
                false,
                "stale_version",
                null
        );
    }

    @ExceptionHandler(DuplicateVersionException.class)
    @ResponseStatus(HttpStatus.OK)
    public ContextResponse duplicateVersion() {

        return new ContextResponse(
                true,
                "duplicate",
                Instant.now().toString()
        );
    }

    @ExceptionHandler(InvalidScopeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public VersionConflictResponse invalidScope() {

        return new VersionConflictResponse(
                false,
                "invalid_scope",
                null
        );
    }
}