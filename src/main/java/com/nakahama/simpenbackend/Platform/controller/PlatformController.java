package com.nakahama.simpenbackend.Platform.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nakahama.simpenbackend.Platform.dto.Ruangan.CreateRuanganRequest;
import com.nakahama.simpenbackend.Platform.dto.Ruangan.ReadDetailRuangan;
import com.nakahama.simpenbackend.Platform.dto.Ruangan.ReadRuangan;
import com.nakahama.simpenbackend.Platform.dto.Ruangan.UpdateRuanganRequest;
import com.nakahama.simpenbackend.Platform.dto.Zoom.CreateZoomRequest;
import com.nakahama.simpenbackend.Platform.dto.Zoom.ReadDetailZoom;
import com.nakahama.simpenbackend.Platform.dto.Zoom.ReadZoom;
import com.nakahama.simpenbackend.Platform.dto.Zoom.UpdateZoomRequest;
import com.nakahama.simpenbackend.Platform.dto.Zoom.ZoomMapper;
import com.nakahama.simpenbackend.Platform.model.Platform;
import com.nakahama.simpenbackend.Platform.service.JadwalService;
import com.nakahama.simpenbackend.Platform.service.PlatformService;
import com.nakahama.simpenbackend.util.ResponseUtil;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/platform")
public class PlatformController {

    @Autowired
    PlatformService platformService;

    @Autowired
    JadwalService jadwalService;

    @PostMapping(value = "", params = "zoom")
    public ResponseEntity<Object> CreateZoom(@RequestParam("zoom") String tipe,
            @Valid @RequestBody CreateZoomRequest createZoomRequest) {

        Platform platform = platformService.save(createZoomRequest);
        return ResponseUtil.okResponse(platform, "Success");
    }

    @PostMapping(value = "", params = "ruangan")
    public ResponseEntity<Object> CreateRuangan(@RequestParam("ruangan") String tipe,
            @Valid @RequestBody CreateRuanganRequest createRuanganRequest) {

        Platform platform = platformService.save(createRuanganRequest);
        return ResponseUtil.okResponse(platform, "Success");
    }

    @GetMapping(value = "", params = "zoom")
    public ResponseEntity<Object> getAllZoom(@RequestParam("zoom") String tipe) {
        List<ReadZoom> listZoom = platformService.getAllZoom();
        return ResponseUtil.okResponse(listZoom, "Success");
    }

    @GetMapping(value = "", params = "ruangan")
    public ResponseEntity<Object> getAllRuangan(@RequestParam("ruangan") String tipe) {
        List<ReadRuangan> listRuangan = platformService.getAllRuangan();
        return ResponseUtil.okResponse(listRuangan, "Success");
    }

    @PutMapping(value = "", params = "zoom")
    public ResponseEntity<Object> UpdateZoom(@RequestParam("zoom") String id,
            @Valid @RequestBody UpdateZoomRequest updateZoomRequest) {

        updateZoomRequest.setId(UUID.fromString(id));
        Platform platform = platformService.update(updateZoomRequest);
        return ResponseUtil.okResponse(platform, "Success");
    }

    @PutMapping(value = "", params = "ruangan")
    public ResponseEntity<Object> UpdateRuangan(@RequestParam("ruangan") String id,
            @Valid @RequestBody UpdateRuanganRequest updateRuanganRequest) {

        updateRuanganRequest.setId(UUID.fromString(id));
        Platform platform = platformService.update(updateRuanganRequest);
        return ResponseUtil.okResponse(platform, "Success");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> DeletePlatform(@PathVariable("id") String id) {
        platformService.delete(UUID.fromString(id));
        return ResponseUtil.okResponse(null, "Success");
    }

    @GetMapping(value = "", params = { "zoom", "kelasId" })
    public ResponseEntity<Object> getAvalaibleZoom(@RequestParam("zoom") String tipe,
            @RequestParam("kelasId") Integer kelasId) {
        List<List<Platform>> listSesiZoom = jadwalService.getAvalaibleZoom(kelasId);
        return ResponseUtil.okResponse(ZoomMapper.toListReadSesiZoom(listSesiZoom), "Success");
    }

    @GetMapping(value = "", params = {"ruangan", "cabang"})
    public ResponseEntity<Object> getAllCabangRuangan(@RequestParam("ruangan") String tipe, @RequestParam("cabang") String cabang) {
        List<String> listCabang = platformService.getDistinctCabang();
        return ResponseUtil.okResponse(listCabang, "Success");
    }

    @GetMapping(value = "/ruangan/{cabang}")
    public ResponseEntity<Object> getAllRuanganByCabang(@PathVariable("cabang") String cabang){
        List<Platform> listRuangan = platformService.getByCabang(cabang);
        return ResponseUtil.okResponse(listRuangan, "Success");
    }

    @GetMapping(value = "", params = {"zoom", "id"})
    public ResponseEntity<Object> getDetailZoom(@RequestParam("zoom") String tipe, @RequestParam("id") String id){
        ReadDetailZoom readDetailZoom = platformService.getDetailZoom(UUID.fromString(id));
        return ResponseUtil.okResponse(readDetailZoom, "Success");
    }

    @GetMapping(value = "", params = {"ruangan", "id"})
    public ResponseEntity<Object> getDetailRuangan(@RequestParam("ruangan") String tipe, @RequestParam("id") String id){
        ReadDetailRuangan ruangan = platformService.getDetailRuangan(UUID.fromString(id));
        return ResponseUtil.okResponse(ruangan, "Success");
    }

    @GetMapping(value = "", params = "jadwal")
    public ResponseEntity<Object> getAllJadwal(){
        return ResponseUtil.okResponse(jadwalService.getAllJadwal(), "Success");
    }

    @PostMapping(value = "/zoom/find")
    public ResponseEntity<Object> findAvailableZoomByDateTime(@RequestBody List<String> listWaktu){
        List<LocalDateTime> listWaktuLocal = convertStringsToLocalDateTimes(listWaktu);
        Platform zoom = jadwalService.findAvailableZoomByDateTime(listWaktuLocal);
        if (zoom == null)
            return ResponseUtil.okResponse(null, "Tidak ada zoom yang tersedia");
        return ResponseUtil.okResponse(zoom, "Success");
    }

    private List<LocalDateTime> convertStringsToLocalDateTimes(List<String> listWaktu) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        return listWaktu.stream()
                .map(dateTimeString -> LocalDateTime.parse(dateTimeString, formatter))
                .collect(Collectors.toList());
    }
}
