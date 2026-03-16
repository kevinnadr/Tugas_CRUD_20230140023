package com.project.Tugas_CRUD_20230140023.service;


import com.project.Tugas_CRUD_20230140023.dto.KtpDto;
import java.util.List;

public interface KtpService {
    KtpDto addKtp(KtpDto request);
    List<KtpDto> getAllKtp();
    KtpDto getKtpById(Integer id);
    KtpDto updateKtp(Integer id, KtpDto request);
    void deleteKtp(Integer id);
}
