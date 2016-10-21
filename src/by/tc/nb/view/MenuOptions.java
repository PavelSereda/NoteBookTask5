package by.tc.nb.view;

import by.tc.nb.bean.*;
import by.tc.nb.bean.entity.Note;
import by.tc.nb.controller.Controller;
import by.tc.nb.dao.connectionsPool.Pool;
import by.tc.nb.service.exception.ServiceException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class MenuOptions {
    int loop = 1;
    Scanner in = new Scanner(System.in);
    char reaction;
    Controller controller;
    int sessionId = 0;
    String menu = "----------------------Menu----------------------\n" +
            "|           Press 1 - Add new note              |\n" +
            "|           Press 2 - Find notes by content     |\n" +
            "|           Press 3 - Find notes by date        |\n" +
            "|           Press 4 - Show all notes            |\n" +
            "|           Press 5 - Clear notebook            |\n" +
            "|           Press 0 - Exit                      |\n" +
            "------------------------------------------------|\n" +
            " Enter a command: ";

    public void menu() throws ServiceException, ClassNotFoundException, ParseException, IOException {

        while (loop != 0) {
            System.out.println(menu);
            reaction = in.nextLine().charAt(0);

            switch (reaction) {

                case '0': {
                    System.out.println("Programm completed successfully");
                    System.out.println("Exit!");
                    Pool.getInstance().closePool();

                    loop = 0;
                }
                break;
                case '1': {
                    AddNoteRequest addNoteRequest = new AddNoteRequest();
                    addNoteRequest.setCommandName("ADD_NEW_NOTE");
                    System.out.println("Your note: ");
                    addNoteRequest.setNote(in.nextLine());
                    addNoteRequest.setUserID(sessionId);
                    Response addNoteResponse = controller.doRequest(addNoteRequest);
                    if (addNoteResponse.isErrorStatus() == true) {
                        System.out.println(addNoteResponse.getErrorMessage());
                    }
                }
                break;

                case '2': {
                    FindNotesRequest findNotesRequest = new FindNotesRequest();
                    System.out.println("Searching content: ");
                    String note = in.nextLine();
                    findNotesRequest.setCommandName("FIND_NOTES_BY_CONTENT");
                    findNotesRequest.setSearchingContent(note);
                    findNotesRequest.setUserID(sessionId);
                    FindNotesResponse findNotesResponse = (FindNotesResponse) controller.doRequest(findNotesRequest);

                    if (findNotesResponse.isErrorStatus() == false) {
                        if (findNotesResponse.getDetectednotes().size() == 0) {
                            System.out.println("There are no such notes");
                        } else {
                            for (Note n : findNotesResponse.getDetectednotes()) {
                                System.out.println(n.getDateStr() + ' ' + n.getNote());
                            }
                        }
                    } else {
                        System.out.println(findNotesResponse.getErrorMessage());
                    }
                }
                break;

                case '3': {
                    FindNotesRequest findNotesRequest = new FindNotesRequest();
                    findNotesRequest.setCommandName("FIND_NOTES_BY_DATE");
                    System.out.println("Searching date (Format Day.Month.Year): ");
                    findNotesRequest.setSearchingDate(in.nextLine());
                    findNotesRequest.setUserID(sessionId);
                    FindNotesResponse findNotesResponse = (FindNotesResponse) controller.doRequest(findNotesRequest);

                    if (findNotesResponse.isErrorStatus() == false) {
                        if (findNotesResponse.getDetectednotes().size() == 0) {
                            System.out.println("There are no such notes");
                        } else {
                            for (Note n : findNotesResponse.getDetectednotes()) {
                                System.out.println(n.getDateStr() + ' ' + n.getNote());
                            }
                        }
                    } else {
                        System.out.println(findNotesResponse.getErrorMessage());
                    }
                }
                break;

                case '4': {
                    ShowNotesRequest showNotesRequest = new ShowNotesRequest();
                    showNotesRequest.setCommandName("SHOW_NOTES");
                    showNotesRequest.setUserID(sessionId);
                    ShowNotesResponse showNotesResponse = (ShowNotesResponse) controller.doRequest(showNotesRequest);
                    if (showNotesResponse.isErrorStatus() == false) {
                        if (showNotesResponse.getNotes().size() == 0) {
                            System.out.println("NoteBook is empty");
                        } else {
                            for (Note n : showNotesResponse.getNotes()) {
                                System.out.println(n.getDateStr() + ' ' + n.getNote());
                            }
                        }
                    } else {
                        System.out.println(showNotesResponse.getErrorMessage());
                    }
                }
                break;


                case '5': {
                    ClearNoteBookRequest clearNoteBookRequest = new ClearNoteBookRequest();
                    clearNoteBookRequest.setCommandName("CLEAR_NOTEBOOK");
                    clearNoteBookRequest.setUserID(sessionId);
                    Response clearNoteBookResponse = controller.doRequest(clearNoteBookRequest);
                    if (clearNoteBookResponse.isErrorStatus() == false) {
                        System.out.println(clearNoteBookResponse.getResultMessage());
                    } else {
                        System.out.println(clearNoteBookResponse.getErrorMessage());
                    }
                }
                break;


                default: {
                    System.out.println("Wrong command. Please, try again");
                }
            }
        }
    }

    public MenuOptions(Controller controller, int sessionId) {
        this.controller = controller;
        this.sessionId = sessionId;
    }
}
