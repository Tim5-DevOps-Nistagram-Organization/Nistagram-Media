package rs.ac.uns.ftn.devops.tim5.nistagrammedia.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.devops.tim5.nistagrammedia.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.devops.tim5.nistagrammedia.model.Media;
import rs.ac.uns.ftn.devops.tim5.nistagrammedia.service.MediaService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping(value = "/media")
public class MediaController {

    private final MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping(path = "/{id}")
    public HttpServletResponse getMedia(@PathVariable Long id, HttpServletResponse response) throws ResourceNotFoundException, IOException {
        File file = mediaService.getMedia(id);
        response.getOutputStream().write(FileUtils.readFileToByteArray(file));
        return response;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_REGULAR') || hasRole('ROLE_AGENT')")
    public ResponseEntity<Long> imageUpload(@RequestParam("file") MultipartFile file) throws IOException {
        Media media = mediaService.upload(file);
        return new ResponseEntity<>(media.getId(), HttpStatus.OK);
    }
}

