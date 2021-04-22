using lab10.Models;

namespace lab10
{
    public class Program
    {
        public static void Main(string[] args)
        {
            Notebook notebook = new();
            Menu menu = new(notebook);
            menu.Run();
        }
    }
}
