using System.Collections.Generic;
using lab10.Interfaces;

namespace lab10.Models
{
    public class Notebook
    {
        private readonly List<INotebookEntry> _entries = new();

        public Notebook Save(INotebookEntry entry)
        {
            _entries.Add(entry);
            return this;
        }

        public int CountEntries()
        {
            return _entries.Count;
        }

        public IEnumerable<INotebookEntry> GetAll()
        {
            return _entries;
        }
    }
}
