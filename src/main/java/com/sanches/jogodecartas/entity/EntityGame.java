package com.sanches.jogodecartas.entity;

import com.sanches.jogodecartas.utils.GameConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "TB_VENCEDORES")
@SequenceGenerator(name = "sq_tb_vencedores", sequenceName = GameConstants.SQ_TB_VENCEDORES, allocationSize = 1)
public class EntityGame {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tb_vencedores")
    @Id
    @Column(name = "ID_RODADA")
    private Long idRodada;

    @Column(name = "VENCEDOR_DA_RODADA")
    private String vencedorDaRodada;

    @Column(name = "PONTUAÇÃO_VENCEDOR")
    private Long pontuacaoVencedor;
}
