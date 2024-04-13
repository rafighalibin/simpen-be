package com.nakahama.simpenbackend.Platform.dto.Zoom;

import com.nakahama.simpenbackend.Platform.model.Zoom;

public class ZoomMapper {
    public static Zoom toEntity(CreateZoomRequest request) {
        Zoom zoom = new Zoom();
        zoom.setNama(request.getNama());
        zoom.setHostKey(request.getHostKey());
        zoom.setLink(request.getLink());
        return zoom;
    }
}
