package dev.Zerphyis.ZeCodesBackEnd.infra.controller;

import dev.Zerphyis.ZeCodesBackEnd.aplication.dtos.RequetsMapper;
import dev.Zerphyis.ZeCodesBackEnd.aplication.dtos.PartnerRequestDto;
import dev.Zerphyis.ZeCodesBackEnd.aplication.dtos.PartnerResponseDto;
import dev.Zerphyis.ZeCodesBackEnd.aplication.service.PartnerServiceImpl;
import dev.Zerphyis.ZeCodesBackEnd.infra.config.rateLimiter.RateLimiterService;
import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;
import dev.Zerphyis.ZeCodesBackEnd.model.exceptions.RateLimiterExecededException;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/partners")
public class PartnerController {

    private final PartnerServiceImpl partnerService;
    private final RateLimiterService rateLimiterService;

    public PartnerController(
            PartnerServiceImpl partnerService,
            RateLimiterService rateLimiterService
    ) {
        this.partnerService = partnerService;
        this.rateLimiterService = rateLimiterService;
    }


    @PostMapping
    public ResponseEntity<PartnerResponseDto> createPartner(
            HttpServletRequest httpRequest,
            @RequestBody @Valid PartnerRequestDto request
    ) {
        String key = httpRequest.getRemoteAddr() + ":POST:/partners";

        Bucket bucket = rateLimiterService.resolveBucket(
                key,
                5,
                Duration.ofMinutes(1)
        );

        if (!bucket.tryConsume(1)) {
            throw new RateLimiterExecededException("Foi realizada muitas requisições aguarde");
        }

        Partner partner = partnerService.createPartner(
                RequetsMapper.toEntity(request)
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(RequetsMapper.toResponse(partner));
    }


    @GetMapping("/{id}")
    public ResponseEntity<PartnerResponseDto> getById(@PathVariable String id) {
        Partner partner = partnerService.getPartnerById(id);
        return ResponseEntity.ok(RequetsMapper.toResponse(partner));
    }


    @GetMapping("/search")
    public ResponseEntity<PartnerResponseDto> searchNearest(
            HttpServletRequest httpRequest,
            @RequestParam("lat") Double latitude,
            @RequestParam("long") Double longitude
    ) {
        String key = httpRequest.getRemoteAddr() + ":GET:/partners/search";

        Bucket bucket = rateLimiterService.resolveBucket(
                key,
                10,
                Duration.ofMinutes(1)
        );

        if (!bucket.tryConsume(1)) {
            throw new RateLimiterExecededException("Limite de requisições excedido.");
        }

        Partner partner = partnerService.searchNearestPartner(latitude, longitude);
        return ResponseEntity.ok(RequetsMapper.toResponse(partner));
    }



    @GetMapping("/active")
    public ResponseEntity<List<PartnerResponseDto>> getActive() {
        return ResponseEntity.ok(
                partnerService.getActivePartners()
                        .stream()
                        .map(RequetsMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<PartnerResponseDto>> getInactive() {
        return ResponseEntity.ok(
                partnerService.getInactivePartners()
                        .stream()
                        .map(RequetsMapper::toResponse)
                        .toList()
        );
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activate(@PathVariable String id) {
        partnerService.activatePartner(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable String id) {
        partnerService.deactivatePartner(id);
        return ResponseEntity.noContent().build();
    }
}
