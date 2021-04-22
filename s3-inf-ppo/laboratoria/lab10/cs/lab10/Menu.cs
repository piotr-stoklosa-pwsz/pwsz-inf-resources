using System;
using lab10.Interfaces;
using lab10.Models;
using lab10.Models.Entries;

namespace lab10
{
    public class Menu
    {
        private readonly Notebook _notebook;

        public Menu(Notebook notebook)
        {
            _notebook = notebook;
        }

        public void Run()
        {
            bool running = true;

            while (running)
            {
                DisplayMenu();
                Write("Wybierz opcję: ");

                switch (Console.ReadLine())
                {
                    case "1":
                        {
                            Write();
                            Comment($"zanotowano: {_notebook.CountEntries()}");
                            foreach (INotebookEntry item in _notebook.GetAll())
                            {
                                Write(item.GetSlug());
                            }
                            break;
                        }
                    case "2":
                        {
                            Write("Podaj tytuł filmu: ");
                            string title = Console.ReadLine();
                            WatchedMovie movie = new WatchedMovie(title);
                            _notebook.Save(movie);
                            break;
                        }
                    case "3":
                        {
                            Write("Podaj nazwę piwa: ");
                            string name = Console.ReadLine();
                            Write("Podaj nazwę browaru: ");
                            string brewery = Console.ReadLine();
                            DrankBeer beer = new(name, brewery);
                            _notebook.Save(beer);
                            break;
                        }
                    case "x":
                        {
                            running = false;
                            break;
                        }
                    default:
                        {
                            break;
                        }
                }
            }
        }

        private void DisplayMenu()
        {
            Write();
            Comment($"notatek: {_notebook.CountEntries()}");
            Write("[1] wypisz notatki");
            Write("[2] zanotuj obejrzany film");
            Write("[3] zanotuj wypite piwo");
            Write("[x] wyjdź");
        }

        private void Write()
        {
            Console.WriteLine();
        }

        private void Write(string line)
        {
            Console.WriteLine(line);
        }

        private void Comment(string line)
        {
            Console.Write(line);
        }
    }
}
