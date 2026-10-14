package POO;

    public class Premium extends Categoria {

        public Premium() {
            super(14, 2.0,3,false);
        }

        @Override
        public String getNome() {
            return "Premium";
        }
    }
