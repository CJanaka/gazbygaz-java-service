package com.gazbygaz.response;

import com.gazbygaz.dto.OutletManagerDto;
import lombok.Data;

import java.util.List;

@Data
public class OutletManagerResponse extends Response{
    private List<OutletManagerDto> outletManagers;
}
