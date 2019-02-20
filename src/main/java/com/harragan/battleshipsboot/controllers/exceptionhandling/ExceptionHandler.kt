package com.harragan.battleshipsboot.controllers.exceptionhandling

import com.harragan.battleshipsboot.service.exceptions.IllegalBoardPlacementException
import com.harragan.battleshipsboot.service.exceptions.IllegalGameStartException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(IllegalBoardPlacementException::class)
    fun handleBoardException(exception: IllegalBoardPlacementException
                             , webRequest: WebRequest): ResponseEntity<String> {
        return ResponseEntity(exception.message!!, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalGameStartException::class)
    fun handleIllegalStartException(exception: IllegalGameStartException
                                    , webRequest: WebRequest): ResponseEntity<String> {
        return ResponseEntity(exception.message!!, HttpStatus.BAD_REQUEST)
    }
}