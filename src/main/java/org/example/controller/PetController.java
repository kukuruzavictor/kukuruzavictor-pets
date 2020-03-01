package org.example.controller;

import j2html.tags.ContainerTag;
import org.example.entity.Pet;
import org.example.service.PetService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static j2html.TagCreator.*;

/**
 * https://readlearncode.com/servlet-4-features/
 * Look at push feature (parallel pushes for content, images, others)
 */
@WebServlet(name ="pets", urlPatterns = "/pets")
public class PetController extends HttpServlet {

    /**
     * Show all pets.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();

        PetService petService = new PetService();
        List<Pet> allPets = petService.getAllPets();
        ContainerTag petHtmlpage = html(
                head(
                        title("Pet List"),
                        link()
                                .withRel("stylesheet")
                                .withHref("https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css")
                ),
                body(
                        div(attrs("#pets"),
                                p("Some sibling element"),
                                each(allPets, pet ->
                                        div(attrs(".pet"),
                                                h2(pet.getName()),
                                                h2(String.valueOf(pet.getAge())),
//                                                img().withSrc(pet.getImage()),
                                                p(pet.getBreed())
                                        )
                                )
                        )
                )
        );
        printWriter.println(petHtmlpage.render());
    }
}
