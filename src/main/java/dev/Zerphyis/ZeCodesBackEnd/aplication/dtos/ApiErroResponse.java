package dev.Zerphyis.ZeCodesBackEnd.aplication.dtos;

import java.time.Instant;

public record ApiErroResponse(int status, String erro, String message, String path, Instant timestamp) {
}
