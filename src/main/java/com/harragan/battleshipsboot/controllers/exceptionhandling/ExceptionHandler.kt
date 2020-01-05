package com.harragan.battleshipsboot.controllers.exceptionhandling

import com.harragan.battleshipsboot.service.exceptions.IllegalBoardPlacementException
import com.harragan.battleshipsboot.service.exceptions.IllegalGameStartException
import com.harragan.battleshipsboot.service.exceptions.IllegalShotException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalBoardPlacementException::class)
    fun handleBoardException(exception: IllegalBoardPlacementException,
                             webRequest: WebRequest): String {
        return exception.message
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalGameStartException::class)
    fun handleIllegalStartException(exception: IllegalGameStartException
                                    , webRequest: WebRequest): String {
        return exception.message
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalShotException::class)
    fun handleIllegalShotException(exception: IllegalShotException
                                    , webRequest: WebRequest): String {
        return exception.message
    }
}
