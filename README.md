# Java Chess

A cleanly architected implementation of chess in Java, designed for flexibility and modularity.

## ❓ Why This Project?

While many chess engines and UIs already exist — including some written in Java — this project is intentionally built
from the ground up as a learning and architectural exercise.

The goal is not to reinvent the wheel, but to use a well-defined problem domain (chess) as a vehicle for:

- Deepening understanding of object-oriented design and the SOLID principles
- Applying clean, scalable architecture to a real-world domain
- Practicing modular design that cleanly separates engine, UI, and AI components
- Exploring algorithmic complexity through AI and move generation
- Getting more familiar with a language (Java) — and its accompanying ecosystem — that I haven't had much experience
  with yet.

Although the initial focus is on standard chess rules, the architecture is intentionally designed to allow for future
adaptations. This includes the possibility of supporting chess variants such as _Chess960_, _King of the Hill_, or
custom board setups — without requiring fundamental changes to the engine. Flexibility and extensibility are key
priorities
throughout.

The project's emphasis is on maintainability, extensibility, and clear separation of concerns — the same qualities
expected in production-grade software.

## 🧠 Goals

- Implement core chess logic with a strong focus on clean design and maintainability
- Build a flexible engine that can support:
    - Console UI
    - GUI frontends (JavaFX)
    - Web frontends (powered by REST API)
    - Arbitrary starting positions and board states
    - Future AI/computer players
- Adhere to SOLID principles and separation of concerns

## 🏗️ Project Structure (Planned)

- [ ] `engine/`: core chess rules and logic (board, pieces, moves)
- [ ] `cli/`: simple console interface for playing chess
- [ ] `fx-ui/`: JavaFX GUI
- [ ] `web-api/`: RESTful API for remote play or frontend integration
- [ ] `web-frontend/`: SPA web frontend (React, Vue or Svelte)
- [ ] `ai/`: computer player logic

It is intended that players will be able to play in three modes on all interfaces (`cli`, `fx-ui`, `web-frontend`):

- single player mode (against the `ai` logic)
- two-player mode on one machine (offline only)
- two-player mode on different machines, using the `web-api` backend

## 🚧 Status

Early stages – engine architecture and core move logic under development.

## 🎯 1.0 Scope

The initial release (`v1.0`) will focus on:

- Full implementation of standard chess rules (including castling, en passant, promotion)
- Console UI (`cli/`) for two-player games
- JUnit test suite for core logic
- Clean, extensible architecture

Planned for later versions:

- Game persistence
- JavaFX UI (local first; backend tie-in later)
- REST API with web frontend
- Computer/AI opponent

## 📖 License

MIT – open and free to use, modify, and extend.
