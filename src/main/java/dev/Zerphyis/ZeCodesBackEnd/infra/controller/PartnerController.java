package dev.Zerphyis.ZeCodesBackEnd.infra.controller;

import dev.Zerphyis.ZeCodesBackEnd.aplication.dtos.RequetsMapper;
import dev.Zerphyis.ZeCodesBackEnd.aplication.dtos.PartnerRequestDto;
import dev.Zerphyis.ZeCodesBackEnd.aplication.dtos.PartnerResponseDto;
import dev.Zerphyis.ZeCodesBackEnd.aplication.service.PartnerServiceImpl;
import dev.Zerphyis.ZeCodesBackEnd.model.entites.Partner;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partners")
public class PartnerController {

    private final PartnerServiceImpl partnerService;

    public PartnerController(PartnerServiceImpl partnerService) {
        this.partnerService = partnerService;
    }

    @PostMapping
    public ResponseEntity<PartnerResponseDto> createPartner(
            @RequestBody @Valid PartnerRequestDto request
    ) {
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
            @RequestParam("lat") Double latitude,
            @RequestParam("long") Double longitude
    ) {
        Partner partner = partnerService.searchNearestPartner(latitude, longitude);
        return ResponseEntity.ok(RequetsMapper.toResponse(partner));
    }

    @GetMapping
    public ResponseEntity<List<PartnerResponseDto>> getAll() {
        return ResponseEntity.ok(
                partnerService.getAllPartners()
                        .stream()
                        .map(RequetsMapper::toResponse)
                        .toList()
        );
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