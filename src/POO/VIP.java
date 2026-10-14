package POO;

    public class VIP extends Categoria {

        public VIP() {
            super(30, 1.0, 10, true);
        }

        @Override
        public String getNome() {
            return "VIP";
        }
    }

