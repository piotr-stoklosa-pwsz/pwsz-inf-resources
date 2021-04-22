using lab10.Interfaces;

namespace lab10.Models.Entries
{
    public class WatchedMovie : INotebookEntry, ISlugger
    {
        private readonly string _title;

        public WatchedMovie(string title)
        {
            _title = title;
        }

        public string GetSlug()
        {
            return (this as ISlugger).GetSlug(_title);
        }
    }
}
