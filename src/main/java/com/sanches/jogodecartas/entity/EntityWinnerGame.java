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
@SequenceGenerator(name = "sq_tb_winners", sequenceName = GameConstants.SQ_TB_VENCEDORES, allocationSize = 0)
public class EntityWinnerGame {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tb_winners")
    @Id
    @Column(name = "ID_ROUND")
    private Long idRound;

    @ManyToOne
    @JoinColumn(name = "ID_GAME")
    private EntityInitializerGame initializerGame;

    @Column(name = "ROUND_WINNER")
    private String roundWinner;

    @Column(name = "SCORE_WINNER")
    private Integer scoreWinner;

    @JsonDeserialize(using = DateAndTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConverterUtil.FORMATO_DATA)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_REGISTER")
    private Date dateRegister;
}
