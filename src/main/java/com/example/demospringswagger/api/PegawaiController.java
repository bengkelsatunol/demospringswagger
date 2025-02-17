package com.example.demospringswagger.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demospringswagger.model.Pegawai;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "pegawai", description = "api pegawai")
@RestController
@RequestMapping("/api/")
public class PegawaiController {

    List<Pegawai> pegawaiList = new ArrayList<>();

    public PegawaiController() {
        for (int i = 0; i <= 4; i++) {
            Pegawai p = new Pegawai();
            p.setId(i);
            p.setNama("pegawai " + p.getId());
            p.setJabatan("karyawan");
            pegawaiList.add(p);
        }
    }

    @Operation(summary = "data pegawai")
    @ApiResponse(responseCode = "200", description = "menampilkan data pegawai")
    @GetMapping("/pegawai")
    public List<Pegawai> pegawaiList() {
        return this.pegawaiList;
    }

    @Operation(summary = "view data pegawai")
    @ApiResponse(responseCode = "200", description = "menampilkan data pegawai")
    @GetMapping("/pegawai/{id}")
    public ResponseEntity<Pegawai> pegawaiById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.pegawaiList.get(id));
    }

    @Operation(summary = "store data pegawai")
    @ApiResponse(responseCode = "200", description = "menambah data pegawai")
    @PostMapping("/pegawai")
    public ResponseEntity<Pegawai> createPegawai(@RequestBody Pegawai pegawai) {
        this.pegawaiList.add(pegawai);
        return ResponseEntity.ok(pegawai);
    }

}
