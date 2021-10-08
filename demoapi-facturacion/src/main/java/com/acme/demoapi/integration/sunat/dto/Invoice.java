package com.acme.demoapi.integration.sunat.dto;

import java.math.BigDecimal;
import java.sql.Date;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {
    
    private String numeroFactura;
    private String rucEmisor;
    private Date fechaEmision;
    private String rucComprador;
    private BigDecimal importe;
    private String status;
}