package com.globant.bootcamp.dia15;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Invalid credentials supplied")
public class UnauthorizedException extends RuntimeException{
}
