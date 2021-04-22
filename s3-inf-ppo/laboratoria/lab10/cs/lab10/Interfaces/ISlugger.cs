using Slugify;

namespace lab10.Interfaces
{
    public interface ISlugger
    {
        string GetSlug(string input)
        {
            var slugHelper = new SlugHelper();
            return slugHelper.GenerateSlug(input);
        }
    }
}
