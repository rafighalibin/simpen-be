package com.nakahama.simpenbackend.Platform.dto.Zoom;

import java.util.ArrayList;
import java.util.List;

import com.nakahama.simpenbackend.Platform.model.JadwalZoom;
import com.nakahama.simpenbackend.Platform.model.Platform;
import com.nakahama.simpenbackend.Platform.model.Zoom;

public class ZoomMapper {
    public static Zoom toEntity(CreateZoomRequest request) {
        Zoom zoom = new Zoom();
        zoom.setNama(request.getNama());
        zoom.setHostKey(request.getHostKey());
        zoom.setLink(request.getLink());
        return zoom;
    }

    public static ReadJadwalZoom toReadJadwalZoom(JadwalZoom request) {
        ReadJadwalZoom response = new ReadJadwalZoom();
        response.setId(request.getId());
        response.setPlatformId(request.getZoom().getId());
        response.setNama(request.getZoom().getNama());
        response.setHostKey(request.getZoom().getHostKey());
        response.setLink(request.getZoom().getLink());
        response.setWaktu(request.getWaktu());
        return response;
    }

    public static ReadZoom toReadZoom(Zoom request) {
        ReadZoom response = new ReadZoom();
        response.setId(request.getId());
        response.setNama(request.getNama());
        response.setHostKey(request.getHostKey());
        response.setLink(request.getLink());
        return response;
    }

    public static List<List<ReadZoom>> toListReadSesiZoom(List<List<Platform>> request) {
        List<List<ReadZoom>> response = new ArrayList<>();
        for (List<Platform> listPlatform : request) {
            List<ReadZoom> listReadZoom = new ArrayList<>();
            if (listPlatform.isEmpty()) {
                response.add(listReadZoom);
                continue;
            }
            for (Platform platform : listPlatform) {
                if (platform instanceof Zoom) {
                    ReadZoom readZoom = toReadZoom((Zoom) platform);
                    listReadZoom.add(readZoom);
                }
            }
            response.add(listReadZoom);

        }
        return response;
    }
}
