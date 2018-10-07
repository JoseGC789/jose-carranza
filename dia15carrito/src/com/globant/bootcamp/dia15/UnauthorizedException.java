package com.globant.bootcamp.dia15;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Inadequate credentials supplied")
public class UnauthorizedException extends RuntimeException{
}
