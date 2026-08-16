package com.example.backend_java.Entity;

import com.example.backend_java.enun.STATUS_SOLICITACAO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.OffsetDateTime;

@Entity
@Table(name = "solicitacoes")
@Setter
@Getter
public class Solicitacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numero_pdv", referencedColumnName = "numero_pdv")
    private Equipamento numeroPdv;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_acao_de_liberacao")
    private AcoesDeLiberacoes idAcaoDeLiberacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_motivo_solicitacao", columnDefinition = "text")
    private String idMotivoSolicitacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_solicitante")
    private Integer idUsuarioSolicitante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_aprovador")
    private Integer idUsuarioAprovador;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private STATUS_SOLICITACAO status;

    @Column(name = "nome_cliente", columnDefinition = "text")
    private String nomeCliente;

    @Column(name = "codigo_cliente")
    private Integer codigoCliente;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "item_cancelado")
    private Integer itemCancelado;

    @Column(name = "limite_excedido")
    private float limiteExedido;


}

