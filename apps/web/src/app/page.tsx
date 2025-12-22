export default function Home() {
  return (
    <main className="main">
      <div className="container">
        <div className="flex min-h-[calc(100vh-6rem)] flex-col items-center justify-center py-12">
          <h1 className="mb-2 text-4xl font-bold">Subscription Tracker</h1>
          <p className="text-muted-foreground mb-6 text-lg">
            A free, open-source app to track recurring subscriptions and personal expenses
          </p>
        </div>
      </div>
    </main>
  );
}
