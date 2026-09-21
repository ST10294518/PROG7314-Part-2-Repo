using Microsoft.EntityFrameworkCore;
using Winx.API.Models;

namespace Winx.API.Data;

public class AppDbContext : DbContext
{
    public AppDbContext(DbContextOptions<AppDbContext> options)
        : base(options)
    {
    }

    public DbSet<TravelEntry> TravelEntries => Set<TravelEntry>();
}