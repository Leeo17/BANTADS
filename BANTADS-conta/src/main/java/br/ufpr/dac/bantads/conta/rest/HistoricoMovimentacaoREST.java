package br.ufpr.dac.bantads.conta.rest;

import br.ufpr.dac.bantads.conta.model.ExtratoPorDiaDTO;
import br.ufpr.dac.bantads.conta.model.HistoricoMovimentacao;
import br.ufpr.dac.bantads.conta.model.HistoricoMovimentacaoDTO;
import br.ufpr.dac.bantads.conta.repository.HistoricoMovimentacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class HistoricoMovimentacaoREST {
    @Autowired
    private HistoricoMovimentacaoRepository repo;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("contas/{numero}/extrato")
    public ResponseEntity<List<ExtratoPorDiaDTO>> buscarExtratoPorData(@PathVariable Long numero,
                                                                              @RequestParam String dataInicio,
                                                                              @RequestParam String dataFim) {
        try {
            Date dataInicioFormatada = new SimpleDateFormat("yyyy-MM-dd").parse(dataInicio);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dataFim));
            calendar.set(Calendar.HOUR, 23);
            calendar.set(Calendar.MINUTE, 59);

            Date dataFimFormatada = calendar.getTime();

            List<HistoricoMovimentacao> listaMovimentacoes = repo
                    .findByNumeroContaOrigemAndDataHoraGreaterThanEqualAndDataHoraLessThanEqualOrderByDataHoraAsc(
                            numero, dataInicioFormatada, dataFimFormatada);

            SimpleDateFormat formatoSaida = new SimpleDateFormat("dd/MM/yyyy");
            Map<String, List<HistoricoMovimentacao>> movimentacoesPorDia = listaMovimentacoes.stream()
                    .collect(Collectors.groupingBy(d -> formatoSaida.format(d.getDataHora()), LinkedHashMap::new, Collectors.toList()));

            List<ExtratoPorDiaDTO> extratoPorDiaDTOs = new ArrayList<>();
            for (String dataChave : movimentacoesPorDia.keySet()) {
                List<HistoricoMovimentacao> listaMovimentacoesDia = movimentacoesPorDia.get(dataChave);
                HistoricoMovimentacao ultimaMovimentacaoDia = listaMovimentacoesDia.get(listaMovimentacoesDia.size() - 1);
                ExtratoPorDiaDTO extratoPorDiaDTO = new ExtratoPorDiaDTO(dataChave, ultimaMovimentacaoDia.getSaldoAposMovimentacao(),
                        listaMovimentacoesDia.stream().map(lista -> mapper.map(lista, HistoricoMovimentacaoDTO.class))
                                .collect(Collectors.toList()));

                extratoPorDiaDTOs.add(extratoPorDiaDTO);
            }

            return ResponseEntity.ok(extratoPorDiaDTOs);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
