import java.time.LocalDateTime;

public class Binario_Hexadecimal {

    //Nombre original date
    //Entonces este código este como trabajar los bits con operadores y mascara
    // temas fechas

    private int f;

    public Binario_Hexadecimal(int dia, int mes, int agno, int hora,int minutos){
        // Toma el valor en bits del int y lo guarda, no presenta problemas porque no se sobreescribe ninguno
        this.f = agno | mes <<12 |dia << 16 | hora << 21 | minutos << 26;
    }


    public int getDia(){
        int va = this.f;
        // los get hacen un desplazamiento de 16 bits para que los datos que importan estén al final, luego se crea una máscara de 4 bits y esta se compara compare
        // con un & para que retorne los valores que importan
        return  (va >> 16) & ((1 << 5) -1);
    }
    public int getMes(){
        int va = this.f;
        return  (va >> 12) & ((1 << 4) -1);
    }
    public int getAgno(){
        int va = this.f;
        return  (va >> 0) & ((1 << 12) -1);
    }
    public int getHora(){
        int va = this.f;
        return  (va >> 21) & ((1 << 5) -1);
    }
    public int getMinutos(){
        int va = this.f;
        return  (va >> 26) & ((1 << 6) -1);
    }

    public void setDia(int a){
        int inicio = 16; // posición del dia en el int
        int longi = 5; // longitud que tiene en bits el dia

        int masc = this.f;

        masc = ~(((1 << longi )-1)<<16); // la máscara se modifica para que todos los bits que se quieren modificar sean 1 y luego saca el complemento esto para luego limpiar
        this.f = (f & masc) | a << inicio; //Limpia el día antiguo, Inserta el nuevo día
    }
    public void setMes(int a ){
        int inicio = 12;
        int longi = 4;

        int masc = this.f;

        masc = ~(((1 << longi )-1)<<12);
        this.f = (f & masc) | a << inicio;
    }
    public void setAgno( int a){
        int inicio = 0;
        int longi = 12;

        int masc = this.f;

        masc = ~(((1 << longi )-1)<<0);
        this.f = (f & masc) | a << inicio;
    }
    public void setHora(int a){
        int inicio = 21;
        int longi = 5;

        int masc = this.f;

        masc = ~(((1 << longi )-1)<<21);
        this.f = (f & masc) | a << inicio;
    }
    public void setMinutos(int a){
        int inicio = 26;
        int longi = 6;

        int masc = this.f;

        masc = ~(((1 << longi )-1)<<26);
        this.f = (f & masc) | a << inicio;
    }


    @Override
    public String toString(){
        return getDia() + "/" + getMes() + "/" + getAgno() + " " +  getHora()+ ":" + getMinutos();
    }

    public boolean isEquals(Binario_Hexadecimal d){
        return this.f == d.f;
    }

    public boolean isBefore(Binario_Hexadecimal d){
        LocalDateTime fecha1 = LocalDateTime.of(getAgno(), getMes(), getDia(), getHora(), getMinutos());
        LocalDateTime fecha2 = LocalDateTime.of(d.getAgno(), d.getMes(), d.getDia(), d.getHora(), d.getMinutos());
        return  fecha1.isBefore(fecha2);
    }
}
