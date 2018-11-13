package com.books.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.books.app.exception.BookAppException;
import com.books.app.model.Book;
import com.books.app.model.Subject;
import com.books.app.service.IBookAppService;

@Controller
public class BookController {

	@Autowired
	IBookAppService bookAppService;

	@RequestMapping("/")
	public String showHome() {
		return "index";

	}
	
	@RequestMapping("/exit")
	public String exitApp() {
		return "exit";

	}

	@RequestMapping("/view")
	public ModelAndView showAllBooks() {
		System.out.println("View all method");
		List<Book> booklist = null;
		try {
			booklist = bookAppService.view();
		} catch (BookAppException e) {
			e.printStackTrace();
		}
		return new ModelAndView("view", "booklist", booklist);
	}

	@RequestMapping("/addBook")
	public ModelAndView showBookForm() {
		return new ModelAndView("addBook", "command", new Book());
	}

/*	@RequestMapping(value = "/saveBook", method = RequestMethod.POST)
	public ModelAndView saveBook(@ModelAttribute("book") Book book, BindingResult result, Model model) {
		try {
			bookAppService.add(book, book.getSubject().getSubtitle());
		} catch (BookAppException e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/view");
	}*/

	@RequestMapping("/searchBookForm")
	public ModelAndView searchBookForm() {
		return new ModelAndView("searchBookForm", "command", new Book());
	}
	
/*	@RequestMapping("/searchBookByTitle")
	public ModelAndView searchBookByTitle() {
		return new ModelAndView("searchBookByTitle", "command", new Book());
	}

	@RequestMapping("/searchBook")
	public ModelAndView searchBook(@ModelAttribute("book") Book book, BindingResult result, Model model) {
		try {
			Book sbook = null;
			if (book.getBookId() != 0) {
				sbook = bookAppService.search(book.getBookId());
			} else if (null != book.getTitle()) {
				sbook = bookAppService.searchByTitle(book.getTitle());
			}

			model.addAttribute("stitle", sbook.getSubject().getSubtitle());
			model.addAttribute("bookId", sbook.getBookId());
			model.addAttribute("title", sbook.getTitle());
			model.addAttribute("price", sbook.getPrice());
			model.addAttribute("volume", sbook.getVolume());

		} catch (BookAppException e) {
			e.printStackTrace();
		}
		return new ModelAndView("searchBook");
	}*/
	

	
	@RequestMapping("/searchSubjectForm")
	public ModelAndView searchSubjectForm() {
		return new ModelAndView("searchSubjectForm", "command", new Subject());
	}

/*	@RequestMapping("/searchSubjectByDuration")
	public ModelAndView searchSubjectByDuration() {
		return new ModelAndView("searchSubjectByDuration", "command", new Subject());
	}

	@RequestMapping("/searchSubject")
	public ModelAndView searchSubject(@ModelAttribute("subject") Subject subject, BindingResult result, Model model) {
		List<Book> booksbysubject = new ArrayList<Book>();

		try {
			Subject subj = null;
			List<Subject> subjList = null;
			if (subject.getSubtitle() != null) {
				subj = bookAppService.searchSubject(subject.getSubtitle());
				getBooksBySubject(booksbysubject, subj);
			} else if (subject.getDurationInHours() != 0) {
				subjList = bookAppService.searchSubjectByDuration(subject.getDurationInHours());
				for (Subject sub : subjList) {
					getBooksBySubject(booksbysubject, sub);
				}
			}
			
		} catch (BookAppException e) {
			e.printStackTrace();
		}
		return new ModelAndView("searchSubject", "booksbysubject", booksbysubject);
	}*/


	private void getBooksBySubject(List<Book> booksbysubject, Subject subj) {
		Set<Book> books = subj.getReferences();
		for (Book sbook : books) {
			booksbysubject.add(sbook);
		}
	}

	@RequestMapping("/deleteBook")
	public ModelAndView deleteBookForm() {
		return new ModelAndView("deleteBook", "command", new Book());
	}

/*	@RequestMapping("/delBook")
	public ModelAndView delBook(@ModelAttribute("book") Book book, BindingResult result, Model model) {
		List<Book> booklist = null;

		try {
			bookAppService.delete(book.getBookId());
			booklist = bookAppService.view();
		} catch (BookAppException e) {
			e.printStackTrace();
		}
		return new ModelAndView("view", "booklist", booklist);
	}*/


	@RequestMapping("/deleteSubject")
	public ModelAndView deleteSubjectForm() {
		return new ModelAndView("deleteSubject", "command", new Subject());
	}

	@RequestMapping("/delSubject")
	public ModelAndView delSubject(@ModelAttribute("subject") Subject subject, BindingResult result, Model model) {
		List<Book> booklist = null;

		try {
			bookAppService.deleteSubject(subject);
			booklist = bookAppService.view();

		} catch (BookAppException e) {
			e.printStackTrace();
		}
		return new ModelAndView("view", "booklist", booklist);
	}
	// other version..
	
	@RequestMapping(value="/searchBookByTitle", method = RequestMethod.GET)
	public ModelAndView searchBookByTitle(@RequestParam("title") String title, Model model) {
		try {
			Book sbook = null;
			sbook = bookAppService.searchByTitle(title);
			
			model.addAttribute("stitle", sbook.getSubject().getSubtitle());
			model.addAttribute("bookId", sbook.getBookId());
			model.addAttribute("title", sbook.getTitle());
			model.addAttribute("price", sbook.getPrice());
			model.addAttribute("volume", sbook.getVolume());

		} catch (BookAppException e) {
			e.printStackTrace();
		}
		return new ModelAndView("searchBook");
	}
	
	@RequestMapping(value = "/searchBook/{id}", method = RequestMethod.GET)
	public ModelAndView searchBook(@PathVariable("id") int id, Model model) {
		try {
			Book sbook = null;
			sbook = bookAppService.search(id);
			
			model.addAttribute("stitle", sbook.getSubject().getSubtitle());
			model.addAttribute("bookId", sbook.getBookId());
			model.addAttribute("title", sbook.getTitle());
			model.addAttribute("price", sbook.getPrice());
			model.addAttribute("volume", sbook.getVolume());

		} catch (BookAppException e) {
			e.printStackTrace();
		}
		return new ModelAndView("searchBook");
	}
	
	@RequestMapping(value = "/searchSubjectByDuration", method = RequestMethod.GET)
	public ModelAndView searchSubjectByDuration(@RequestParam("durationInHours") int durationInHours, Model model) {
		
		try {
			List<Subject> subjList = null;
			subjList = bookAppService.searchSubjectByDuration(durationInHours);			
			model.addAttribute("subjList",subjList);
			
		} catch (BookAppException e) {
			e.printStackTrace();
		}
		return new ModelAndView("searchSubjectByDuration");
	}

	@RequestMapping(value="/searchSubject", method = RequestMethod.GET)
	public ModelAndView searchSubject(@RequestParam("subtitle") String subtitle, Model model) {
		if (subtitle != null) {
			Subject subj = null;
			try {
				subj = bookAppService.searchSubject(subtitle);
				model.addAttribute("subtitle", subj.getSubtitle());
			} catch (BookAppException e) {
				e.printStackTrace();
			}
		}
		return new ModelAndView("searchSubject");
	}
	
	@RequestMapping(value = "/saveBook", method = RequestMethod.POST)
	public ModelAndView saveBook(@SessionAttribute("book") Book book, Model model) {
		try {
			String title = bookAppService.add(book, book.getSubject().getSubtitle());
			model.addAttribute("title", title);
		} catch (BookAppException e) {
			e.printStackTrace();
		}
		return new ModelAndView("view");
	}
	
	@RequestMapping(value = "/delBook/{id}", method = RequestMethod.DELETE)
	public ModelAndView delBook(@PathVariable("id") int id, Model model) {
		try {
			bookAppService.delete(id);			
		} catch (BookAppException e) {
			e.printStackTrace();
		}
		return new ModelAndView("view");
	}

}
