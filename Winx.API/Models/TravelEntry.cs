namespace Winx.API.Models;

public class TravelEntry
{
    public int Id { get; set; }

    public string Title { get; set; } = string.Empty;

    public string Location { get; set; } = string.Empty;

    public string Country { get; set; } = string.Empty;

    public int Rating { get; set; }

    public string Notes { get; set; } = string.Empty;
}