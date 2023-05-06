package com.sanches.jogodecartas.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sanches.jogodecartas.utils.ConverterUtil;
import com.sanches.jogodecartas.utils.DateAndTimeDeserializer;
import com.sanches.jogodecartas.utils.GameConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "TB_VENCEDORES")
@SequenceGenerator(name = "sq_tb_vencedores", sequenceName = GameConstants.SQ_TB_VENCEDORES, allocationSize = 1)
public class EntityWinnerGame {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tb_vencedores")
    @Id
    @Column(name = "ID_RODADA")
    private Long idRodada;

    @ManyToOne
    @JoinColumn(name = "ID_GAME")
    private EntityInitializerGame initializerGame;

    @Column(name = "VENCEDOR_DA_RODADA")
    private String vencedorDaRodada;

    @Column(name = "PONTUAÇÃO_VENCEDOR")
    private Long pontuacaoVencedor;

    @JsonDeserialize(using = DateAndTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConverterUtil.FORMATO_DATA)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_REGISTER")
    private Date dateRegister;
}
