package com.techvg.courtcase.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.techvg.courtcase.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HearingTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Hearing.class);
        Hearing hearing1 = new Hearing();
        hearing1.setId(1L);
        Hearing hearing2 = new Hearing();
        hearing2.setId(hearing1.getId());
        assertThat(hearing1).isEqualTo(hearing2);
        hearing2.setId(2L);
        assertThat(hearing1).isNotEqualTo(hearing2);
        hearing1.setId(null);
        assertThat(hearing1).isNotEqualTo(hearing2);
    }
}
