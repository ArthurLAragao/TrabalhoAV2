package POO;

public class Restricoes {
        private String emprestar;
        private String email;
        private String categoria;
        private String multa;
        private String renovacao;


        Restricoes(String emprestar, String email, String categoria, String multa, String renovacao ){

            this.emprestar = emprestar;
            this.email = email;
            this.categoria = categoria;
            this.multa = multa;
            this.renovacao = renovacao;


        }


        String getEmprestar() {
            return emprestar;
        }

        void setEmprestar(String nemprestar) {
            emprestar = nemprestar ;
        }

        String getEmail() {
            return email;
        }

        void setEmail(String nemail) {
            email = nemail;
        }

        String getCategoria() {
            return categoria;
        }

        void setCategoria(String ncategoria) {
            categoria = ncategoria ;
        }

        String getMulta() {
            return multa;
        }

        void setMulta(String novaMulta) {
            multa = novaMulta;
        }

        String getRenovacao() {
            return renovacao;
        }

        void setRenovacao(String nRenovacao) {
            renovacao = nRenovacao ;
        }

    }

