package someExercises;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
public class Rko {
    @Getter
    private Long id;
    @Setter
    private String filial;
    @Setter
    private String account;
    @Getter
    private int cntIncome;

    public Rko(Long id, String filial) {
        this.id = id;
        this.filial = filial;
        this.account = "407028103"+filial+ String.format("%08d", id);
        this.cntIncome = 0;
    }
    @Override
    public String toString() {
        return "Rko{" +
                "id=" + id +
                ", filial='" + filial + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
