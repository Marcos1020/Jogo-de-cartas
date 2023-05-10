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
@Table(name = "TB_INITIALIZER_GAME")
@SequenceGenerator(name = "sq_tb_initializer_game", sequenceName = GameConstants.SQ_INITIALIZER_GAME, allocationSize = 0)
public class EntityInitializerGame {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tb_initializer_game")
    @Id
    @Column(name = "ID_GAME")
    private Long idGame;

    @Column(name = "DECK_ID")
    private String deckId;

    @Column(name = "REMAINING")
    private Integer remaining;

    @JsonDeserialize(using = DateAndTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConverterUtil.FORMATO_DATA)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_REGISTER")
    private Date dateRegister;
}
