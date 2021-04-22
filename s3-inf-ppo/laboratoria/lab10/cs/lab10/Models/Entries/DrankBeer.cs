using lab10.Interfaces;

namespace lab10.Models.Entries
{
    public class DrankBeer : INotebookEntry, ISlugger
    {
        private readonly string _name;
        private readonly string _brewery;

        public DrankBeer(string name, string brewery)
        {
            _name = name;
            _brewery = brewery;
        }

        public string GetSlug()
        {
            return (this as ISlugger).GetSlug(_brewery + " " + _name);
        }
    }
}
