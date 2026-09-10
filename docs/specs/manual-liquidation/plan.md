# Plan — manual liquidation

> Phase 3. How the slice specified in `spec.md` gets built, and what was rejected.
>
> Owner: Rumpel · Written: 2026-09-10

## Approach

Four parts, and the line between them is what this plan is about.

- **The engine.** Plain Java, inside the project but depending on nothing around it — not Spring, not
  the database, not the text files. It receives a case and the parameters of the tax year already
  read for it, and returns the liquidation. It fetches nothing and it knows no language: every line
  it returns carries an identifier, its Form 210 box, the article behind it and the inputs it came
  from. It is the piece the constitution says must not be rewritten, and it is testable on its own,
  in seconds, without a server.
- **The API.** Spring Boot around the engine: it receives the request, validates it, reads the year's
  parameters from the database, calls the engine, and answers. It stores the out-of-scope report and
  the consented email. It holds no half-filled case between steps.
- **The interface.** React with TypeScript. It holds what the user types until they ask for a
  liquidation, and it turns every identifier the server returns into a phrase, in the language the
  user chose.
- **The deployment.** The owner's server, served over HTTPS. It exists before the engine does: slice
  zero (D50) is the skeleton deployed end to end, so the first deployment is met without the engine
  on top of it.

## Coverage

| AC | Where it lives | Notes |
|---|---|---|
| AC1 — Not obliged | Engine resolves the five thresholds; API exposes the gate; interface shows the result | The five: income, net worth, credit-card spending, bank movements, purchases |
| AC2 — Correct liquidation | Engine | The three real returns run locally; CI runs the invented case (D49) |
| AC3 — Result visible | Engine returns each line with its box; interface renders | The box travels with the value, never looked up separately (D52) |
| AC4 — Traceability | Engine returns, per value, how it was computed and the article | Article identifiers come from the year's parameters |
| AC5 — Out of scope | Engine decides what it does not cover; API stores the report; interface shows the notice and the consent | |
| AC6 — Missing or malformed field | Interface, as the user types | The server repeats the check on its own (AC14) |
| AC8 — Several certificates | Engine sums, and the trace keeps which document each figure came from | |
| AC9 — Tax year | Parameters table keyed by tax year; API reads one year's set and hands it over | The engine sees one year's parameters and nothing else, so no other year can leak in |
| AC10 — Session | Interface: the case survives a reload in the tab, and closing the tab is what loses it | Tab memory only (D55) |
| AC11 — Disclaimer | Interface text | Wording still deferred — spec open question 3 |
| AC12 — Language | Interface text files, one per language; the server sends identifiers only (D53) | |
| AC13 — Taxpayer choices | Engine resolves every available option with its figure; interface presents them and asks again with the chosen one | |
| AC14 — Validation on the server | API | Type and range, before the engine is called |
| AC15 — Call limit | API | Covers the liquidation and the out-of-scope form alike |
| AC16 — Nothing of the user in the logs | API configuration | Verified by provoking a failure, not by reading the code |
| AC17 — Email format | API | |
| AC18 — Consent on record | Reports table | The consent and its date stored beside the email |
| AC19 — Report already registered | API, over a key of email plus the set of uncovered fields | A different set is always a new entry |
| AC20 — Report state | Reports table | pending / covered / notified; moved by hand until roadmap item 11 |
| AC21 — The server writes the report | Engine names the uncovered fields from a closed list; API stores those identifiers | Nothing the browser sends is stored as text |
| AC22 — Encrypted transport | Deployment | Verified on the deployment, and first met in slice zero |
| AC23 — Privacy notice | Interface, at the point the email is asked for, plus the deletion channel | Law 1581 applies from this slice (D57) |

AC7 is not in this table: it left the slice for the exógena epic (D56).

## Trust boundaries and assets

| Boundary | What enters | What is worth taking | Control | Answers |
|---|---|---|---|---|
| The API's liquidation endpoint | The figures the user transcribed | Nothing stored — the figures live only for the length of the call. What is worth attacking is the service itself and its budget | Type and range validated on the server before the engine is called; call limit per origin; no request body in any log or error dump | AC14, AC15, AC16 |
| The out-of-scope form | An email and the user's consent | The list of emails, which is the only personal data the slice stores | Format validated on the server; consent and date stored beside the email; the same call limit; repeated report updates instead of inserting | AC17, AC18, AC15, AC19 |
| The out-of-scope report | Nothing from the browser | The report table's integrity, since it is what prioritizes coverage | The engine names the uncovered fields from a closed list the project defines; the browser's list is never stored as text | AC21 |
| The connection | Everything, in transit | The figures of whoever shares a network with the user | Served only over HTTPS; an unencrypted request is refused | AC22 |

**Accepted risk, carried from the spec.** Someone enters a third party's email. No control
proportional to this slice answers it; revisited when the notification capability exists.

## Decisions

Recorded in full in `docs/decisions.md`, with their rejected alternative. Listed here so the design
can be read without leaving the file.

| # | Chosen |
|---|---|
| D47 | The legal parameters live in the database, not in versioned files |
| D48 | No change history for the parameters while the repository's initial load is their only writer |
| D49 | CI runs an invented reference case; the three real returns run locally, by the owner |
| D50 | Slice zero — the deployed skeleton over HTTPS — comes before the engine |
| D51 | The engine is plain Java inside the project, depending on nothing around it |
| D52 | The engine returns each line with its box, its article and its inputs attached |
| D53 | The server sends identifiers; every phrase lives in the interface's text files |
| D54 | Verification is split by nature: browser for what the user sees, engine directly for the calculation, API and deployment for the server criteria — correcting the scope of D45 |
| D55 | What the user types is held in tab memory only |
| D56 | AC7 leaves this slice for the exógena epic |
| D57 | Law 1581 applies from this slice, because it stores an email |

## New concepts

- **An engine that depends on nothing** — plain Java, no Spring, no database, no languages. It
  receives what it needs already read and returns a result. It is what makes hundreds of test cases
  run in seconds instead of minutes, and what lets everything around it change without touching it.
- **Identifiers instead of phrases** — the server names a line, the interface decides how that line
  reads in each language. A third language becomes a text file, not a change to the server.
- **Tab memory** — the browser can hold what the user typed for as long as the tab is open. It
  survives a reload; it dies when the tab closes, so nothing of theirs is left on the machine.
- **Two kinds of automated test** — one drives a real browser and is slow, one calls the engine
  directly and is fast. Which criterion goes to which is D54.

## What this makes stale

- `CONTRIBUTING.md`, Dev setup — cites D44, replaced by D47.
- The spec's AC10 — the warning is about closing the tab, not about reloading.
- The spec's AC7 and its failure-state row — they leave for the exógena epic.
- The spec's entry screen — "nothing is saved" is true of the server and no longer of the browser.
- `PLAN.md` §5 and the glossary — four thresholds where there are five, in the law and in the
  exógena summary alike.
- `PLAN.md` §5 and risk R-6 — Law 1581 placed in P2, when the first slice already stores an email.
- `docs/domain/rules.md` and the rule sheets do not go stale, but they gain a consumer: the article
  each sheet cites is the one the engine returns with its line.

## Risks

- **The trace drifts from the rule sheets.** A sheet changes its article and the engine keeps
  returning the old one. It shows early if the articles come from the year's parameters and the
  golden tests assert the trace, not only the figure.
- **The invented case gives false confidence.** It is built by the same hands as the engine, so it
  can share a misunderstanding with it. It never carries the authority of the three real returns
  (D03, D49), and the owner's local run before each delivery is what catches that.
- **The parameters table without history.** Deferred knowingly (D48); the day the admin panel is
  built, the history is part of it. Roadmap item 12 carries the reminder, not anyone's memory.

---

**Exit gate**

- [x] Every acceptance criterion has a home
- [x] Trust boundaries named, with a control answering each abuse case from the spec
- [x] Decisions record the rejected alternative
- [x] New concepts explained and understood
- [x] Stale artifacts listed
- [x] No `[NEEDS CLARIFICATION]` marker is left unresolved
- [x] Every claim here was confirmed in conversation before it was written down
