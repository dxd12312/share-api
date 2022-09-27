package com.dxd.content.domain.dto;


import com.dxd.content.domain.entity.Share;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ShareDto {
    private Share share;
    private String nickname;
    private String avatar;
}
