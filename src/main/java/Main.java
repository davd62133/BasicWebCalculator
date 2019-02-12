import java.util.LinkedList;
import static spark.Spark.*;

public class Main {
    public static void main(String args[]){
        port(getPort());
        staticFiles.location("/");
        index();
        results();
    }

    /**
     * Results of the entered data
     */
    private static void results() {
        post("/resultados", (request, response) -> {
            LinkedList<Double> nums = new LinkedList<>();
            String html = "<html>"
                    +"<head>"
                    +"<title>Resultados</title>"
                    +"</head>"
                    +"<body>"
                    +"<h2>Resultados</h2><p>";
            try{
                for(String s : request.queryParams("uploadText").split(";")){
                    nums.add(Double.parseDouble(s));
                }
                JSON.list = nums;
                JSON.max = max(nums);
                JSON.min = min(nums);
                JSON.sum = sum(nums);
                JSON.mult = mult(nums);
                html +=
                        "Datos ingresados: " + JSON.list + "<p>"
                                +"El maximo de los datos es: " + JSON.max + "<p>"
                                +"El minimo de los datos es: " + JSON.min + "<p>"
                                +"La sumatoria de los datos es: " + JSON.sum + "<p>"
                                +"La multiplicación de los datos es: " + JSON.mult + "<p>"
                                +"El JSON es: " + JSON.getJSON() + "<p>"
                                +"<a href=\"/\"><button>Volver</button></a>"
                                +"</body>"
                                +"</html>";
            }catch (Exception e){
                html +=
                        "Ingreso mal los resultados, recuerde que para un decimal es un punto ('.'), y no se puede ingresar cadenas de letras <p>"
                                +"<a href=\"/\"><button>Volver</button></a>"
                                +"</body>"
                                +"</html>";
            }
            return html;
        });
    }

    static int getPort(){
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    /**
     * Configures the index of the page
     */
    public static void index(){
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Calculadora Basica</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h2>Calculadora Basica</h2>\n" +
                "<form method='POST' action=\"/resultados\">\n" +
                "    <input type='text' name='uploadText'>\n" +
                "    <p>\n" +
                "    <button>Ingresar numeros</button>\n" +
                "</form>\n" +
                "Para ingresar los numeros separelos por \";\"\n" +
                "<p>\n" +
                "EJ:\n" +
                "<p>\n" +
                "3.1;242;32.52;23 (NOTA: los decimales se separan por puntos)\n" +
                "</body>\n" +
                "</html>";
        get("/", (req,res)->html);
    }

    /**
     * Returns the max of a Double list
     * @param nums
     * @return
     */
    public static double max(LinkedList<Double> nums){
        Double max = nums.get(0);
        for (Double d : nums){
            if(d>max) max = d;
        }
        return max;
    }

    /**
     * Returns the minimun of a Double list
     * @param nums
     * @return
     */
    public static double min(LinkedList<Double> nums){
        Double min = nums.get(0);
        for (Double d : nums){
            if(d<min) min = d;
        }
        return min;
    }

    /**
     * Retuns the sum of a Double list
     * @param nums
     * @return
     */
    public static double sum(LinkedList<Double> nums){
        Double sum = 0.0;
        for (Double d : nums){
            sum += d;
        }
        return sum;
    }

    /**
     * Returns the multiplicaction of a Double List
     * @param nums
     * @return
     */
    public static double mult(LinkedList<Double> nums){
        Double mult = 1.0;
        for (Double d : nums){
            mult *= d;
        }
        return mult;
    }

}



