import POO.Membro;
import POO.Padrao;
import POO.Premium;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    Membro joao = new Membro("Joao", 2, "joao@email.com", new Padrao());
    Membro maria = new Membro("Maria", 2, "maria@email.com", new Premium());


}

