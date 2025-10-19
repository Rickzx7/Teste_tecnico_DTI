package br.com.testedit.teste_tecnico_dti;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LogTest {

    private static final Logger log = LoggerFactory.getLogger(LogTest.class);

    @Test
    void testLogs() {
        log.info("=== TESTANDO LOGS ===");
        log.info("Este é um log de INFO");
        log.warn("Este é um log de WARN");
        log.error("Este é um log de ERROR");
        log.debug("Este é um log de DEBUG (não aparece porque está em INFO)");
        log.info("=== FIM DOS TESTES ===");
    }
}
