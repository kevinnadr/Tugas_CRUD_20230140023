package com.project.Tugas_CRUD_20230140023.mapper;


import com.project.Tugas_CRUD_20230140023.dto.KtpDto;
import com.project.Tugas_CRUD_20230140023.entity.Ktp;
import org.springframework.stereotype.Component;

@Component
public class KtpMapper {
    public KtpDto toDto(Ktp entity) {
        KtpDto dto = new KtpDto();
        dto.setId(entity.getId());
        dto.setNomorKtp(entity.getNomorKtp());
        dto.setNamaLengkap(entity.getNamaLengkap());
        dto.setAlamat(entity.getAlamat());
        dto.setTanggalLahir(entity.getTanggalLahir());
        dto.setJenisKelamin(entity.getJenisKelamin());
        return dto;
    }

    public Ktp toEntity(KtpDto dto) {
        Ktp entity = new Ktp();
        entity.setNomorKtp(dto.getNomorKtp());
        entity.setNamaLengkap(dto.getNamaLengkap());
        entity.setAlamat(dto.getAlamat());
        entity.setTanggalLahir(dto.getTanggalLahir());
        entity.setJenisKelamin(dto.getJenisKelamin());
        return entity;
    }
}