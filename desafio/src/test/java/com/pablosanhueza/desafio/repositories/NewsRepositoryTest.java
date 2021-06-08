package com.pablosanhueza.desafio.repositories;

import com.pablosanhueza.desafio.entities.News;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class NewsRepositoryTest {

    @Autowired
    private NewsRepository underTest;

    @Test
    void itShouldCheckIfNewsNameExists() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("2021-06-07T15:14:27-03:00", formatter.ISO_DATE_TIME);

        //given

        News news = new News(
                1L,
                "https://www.infomoney.com.br/colunistas/convidados/politica-do-terceiro-filho-na-china-tres-pequenos-imperadores-um-ja-basta/",
                "Política do terceiro filho na China: três pequenos imperadores? Um já basta!",
                "Medida do governo chinês em permitir às famílias terem o terceiro filho veio obviamente na direção correta, mas aparentemente com enorme atraso",
                "Roberto Dumas Damas",
                dateTime,
                "No dia 31 de maio, o PCC (Partido Comunista Chinês) passou a permitir que as famílias chinesas possam ter três filhos, em vez de apenas dois, medida esta determinada no ano de 2016.\n" +
                        "Primeiramente, é importante deixar claro que o fato de a política de filho único na China ter sido implantada no final dos anos 70 pelo então líder Deng Xiaoping não significa que se alguma família tivesse mais filhos esse herdeiro adicional seria exilado ou retirado de seus país.\n" +
                        "Coisa de lendas urbanas e de quem pouco entende de China, ou preguiça, ou tudo junto. Na realidade, em se tratando do primeiro filho, o governo chinês tende a oferecer maior respaldo social e assistência médica gratuita para alguns casos, o que não impede que desde então chineses mais abastados não pudessem ter três, quatro ou cinco filhos. A única diferença era que o custo para a criação desses filhos seria totalmente coberto pelos pais, e não em partes pelo governo.\n" +
                        "\n" +
                        "Eis que depois de tanto tempo da regra do filho único, que como dissemos foi estendida para dois filhos em 2016, o PCChinês percebeu que a população crescia à média de 0,5% ao ano nos últimos 10 anos, e que a taxa de fecundidade havia caído para 1,3 filho por mulher em idade fértil, considerado bem abaixo do limiar para renovação de gerações.\n" +
                        "De tal sorte, que, tardiamente, o PCChinês passou a oferecer assistência ao terceiro filho e o que se noticia é que o governo permite agora que casais possam ter três filhos. Mas antes de entrarmos no aspecto do “tardiamente”, vamos analisar o porquê dessa medida:\n" +
                        "Essa medida do governo chinês em permitir às famílias terem o terceiro filho veio obviamente na direção correta, mas aparentemente com enorme atraso."
        );
        underTest.save(news);

        //when

        List<News> expected = underTest.findByName("-na-china");

        //then
        assertFalse(expected.isEmpty());







    }
}