interface SearchFilterProps {
  query: string;
  onChange: (query: string) => void;
}

export function SearchFilter({ query, onChange }: SearchFilterProps) {
  return (
    <div className="search-filter">
      <input
        type="text"
        placeholder="Search todos by title..."
        value={query}
        onChange={(e) => onChange(e.target.value)}
        className="todo-input"
      />
    </div>
  );
}
