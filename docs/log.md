# Log

> **What goes in this document:** what happened, dated, newest first. Append-only.
>
> Not here: why a decision was taken (`docs/decisions.md`), what is pending
> (`docs/roadmap.md`). No taxpayer figures — golden-test values stay in the local fixture,
> never in this repository (`memory/constitution.md`, principle 3).

## 2026-09-03 — The stack is chosen

Java 21 with Spring Boot for the engine and its API, React with TypeScript for the interface,
user-facing text in per-language key files, and a database holding only consented emails and
out-of-scope reports (D44). The rejected alternative was running the engine in the browser with no
server at all, which fits this slice better on its own terms — the figures would never leave the
device and there would be one thing to deploy — but the paid tier needs a server for accounts,
payment and exógena, so that engine would be rewritten within months, and the engine is the one
piece that must not be rewritten.

What decided the language was not how fast it is to write. The owner will not be typing this code,
so what counts is how cheaply it can be verified by reading: strict types, and decimal arithmetic
that is exact by default in a calculation that has to match to the peso.

The cost is explicit. The user's figures now travel to a server even though nothing is stored, so
"nothing is kept" has to hold for the server's logs too — verified, not promised.

Item 2's other half closed the same day. A running UI is inspected and evidenced with Playwright
(D45): it drives a real browser while the work is being built, and it leaves the acceptance criteria
as end-to-end tests that re-run on every change. The owner's own look at the screen stays as the
closing check of each slice, never as the only mechanism — the engine's tests cannot see a broken
screen.

## 2026-09-03 — The first slice gets a spec

The interview the previous session left open was finished, and its conclusions are
`docs/specs/manual-liquidation/spec.md` — the first spec this project has, and the reason
`docs/specs/` exists (D39). Spec directories are named without a number: execution order already
lives in the roadmap, and the topic-based numbering carried from the discovery sessions had already
made the first spec to be written `003`. The handoff file that carried the interview between
sessions was emptied into the spec and deleted.

Four decisions came out of it. The slice runs as guest and never persists a fiscal figure; what it
does store is the email a user consents to give when their case is not covered, and the report of
which fields were not covered (D40). That report turned out to be the only mechanism the repository
has for constitution principle 9, which grows coverage case by case without ever saying how those
cases are learned (D41). The line-by-line detail carries each value's Form 210 box number (D42),
superseding D07: the user copies the figures with the form open anyway, so hiding the box makes the
result harder to verify rather than safer. The disclaimer that D07's deferral was protecting against
is now an acceptance criterion, which pulls US-P2-003 out of the P2 epics.

The slice also grew three things no earlier document had: more than one certificate per return, the
user choosing among the three tax years — which the golden tests needed anyway — and both interface
languages from the first version, with every user-facing string behind a key.

Two roadmap entries closed by being answered rather than done: the M1 "key lines" question, fixed by
the spec as every line of the general schedule and of the closing block, and the order of the three
specs, fixed by building the engine first.

Found while writing: two rows in `docs/decisions.md` carried the id D37 — two different decisions,
one identifier — against the file's own rule that ids are never reused. The later row, the one that
put code ahead of P0, became D43.

## 2026-09-02 — The registry learns what the rule sheets say

Thirteen rule sheets existed and the registry marked one of them closed. The status column was
never updated after the sheets were written, so the roadmap still carried "write the rule sheets"
as the item blocking P0 when the writing was already done.

Correcting it surfaced the larger half. Ten of the thirteen sheets carry an `**Open:**` line inside
their conditions, and the registry's flag section knew three of them. The seven it did not know —
R2, R3, R4, R5, R6, R15 and R16 — are now listed there as an index: the full statement of each flag
stays in its own sheet, so the text has one copy and the list has one place.

The status axis was redefined around that. A rule closes when its sheet exists **and** every flag
the sheet raises is resolved or accepted in writing. 🟡 now means the sheet is written with flags
open — what R8 and R10 always were, and what R9 became on rising from ⬜. Three rules are closed:
R1, R7 and R17.

R18 keeps its row and closes inside R3's sheet rather than getting one of its own (D38). The registry
indexes every rule the engine implements, and the engine does add the solidarity fund to the
mandatory contributions; a second sheet repeating R3's formula and test case would have been the
next thing to drift apart.

Found while checking: R4's flag is the one that reaches the first slice. Art. 206 par. 5 extends
the 25% exemption to professional fees, and the MVP scope includes an employee with fees income
opting for the benefit — so that flag is not deferrable to a later case the way the other six are.

Later the same day R2's flag became the first of the ten to close, pulled by the spec interview
rather than by the registry: the first slice's entry gate needed to know whether the 80% condition
of art. 593 reaches the five thresholds. It does not — the salaried and the general categories of
DUT 1625 art. 1.6.1.13.2.7 carry identical thresholds, so the condition selects the numeral that
exempts, never the outcome. The engine derives the labor share from figures the flow already
captures, never asks for it, and cites the exempting numeral with the not-obliged result; the two
categories keep separate, today-equal threshold parameters. Nine flags remain.

## 2026-09-01 — Second batch of answers: R9, R17 and R18 unblocked, R1 closed

R1's rounding question is settled. The UVT is a unit of measure, so a threshold resolves to the
exact product; art. 868 rounding reaches only the peso figures computed from it, where art. 577
governs. Checking it against the engine reversed the expected direction of the fix: the Excel
already resolves every cap exactly, and five rule sheets had rounded the caps to thousands when
they were written. R3, R4, R5, R6 and R7 were corrected against the engine, together with the test
cases quoting a cap.

R9 stopped being blocked without the number being explained. The advance base is not an undecided
method: from the third return onwards the filer chooses between 75% of the current year's tax and
the two-year average, and both are lawful. What survives is narrower — neither reproduces the
amount settled in the reference case, so which base was used is unknown. That the choice belongs
to the filer is a product requirement and is recorded in `docs/reference/calc-model.md`.

R17 documents the two mechanisms of art. 126-4 coexisting per contribution, and fixes the MVP
boundary: the non-compliant withdrawal is not modelled. R18 rose from doctrine alone to doctrine
plus DIAN administrative practice, and its content lives in R3's sheet.

Thirteen rule sheets now exist. The only flags left are R8's decree and R10's filing date.

Found while writing R17: the share of a contribution lost to the general cap may never have
produced a tax benefit, which is a second reason to keep `lost_to_cap` traceable per tax year.

## 2026-08-27 — `CONTEXT.md` emptied and deleted

The session export carried ten sections. Four were already absorbed and died with the file: the
discovery decisions were D09–D20, D25 and D26; the rule sheet status was already in
`docs/domain/rules.md`; the next-steps list and the privacy note had been superseded by the
roadmap and by principle 11.

Two became repository documents: `docs/reference/spec-001-outline.md` and
`docs/reference/calc-model.md`. The second was first written as a description of the Excel
workbook and rejected on the grounds that the workbook never enters the repository, so no reader
can see what it describes. Rewritten as the model the code must implement — parameters versioned
by year, inputs carrying value plus support plus Form 210 line, the five-step depuration pipeline,
the 220→210 map — it stands on its own. The tool-specific quirks and the regression history were
dropped: the first dies with the spreadsheet, the second was already in this log.

The remaining four sections are real taxpayer data and stayed local, as
`docs/context/reference-case.md`.

## 2026-08-27 — Privacy line drawn, repository scrubbed

Nothing committed may identify the owner or a tester; everything written in the repository is an
illustrative example, even when modelled on a real case (D31). Personal tax administration is not
roadmap work and left this repository (D32) — the TY2025 filing item went with it, keeping only
the domain half as roadmap item 3.

Four violations were already public and were removed: a filing calendar tied to a real tax id, a
named financial provider, one real figure from a reference return sitting in the R9 flag, and the
two personal-admin roadmap items. First-person phrasing was rewritten across `PLAN.md`,
`memory/constitution.md`, `docs/domain/rules.md`, `docs/reference/backlog-p0.md` and two accepted
decision rows — redactions of wording, not changes of meaning, which is why D30 was not treated as
blocking them.

The rule became constitution principle 11, and it reaches the next item of work directly: every
rule sheet's numeric example is an invented figure, and the exógena↔return pairs collected from other
people (item 12) never enter the repository.

The real source documents stay in the repository folder, gitignored — they are the reference the
app is built against, and they go when it no longer needs them (D33, roadmap item 11). What
`.gitignore` does not cover is content *derived* from them, which is what D31 governs;
`CONTEXT.md` is the existing example of that failure and is roadmap item 1.

Found while scrubbing: the roadmap said nine rules were at 🟡 and the registry holds ten.

## 2026-08-24 — Documentation reorganized around `/method`

The repository was carrying four documents inside `PLAN.md` and three competing methodologies.
Split into `PLAN.md` (framing), `docs/roadmap.md`, `docs/decisions.md`, this log, and
`docs/domain/rules.md`. The Excel-era user stories moved to `docs/reference/backlog-p0.md` as
input material for the future engine spec. Process now lives only in the `/method` skill.

Six rules were contributed back to `/method` from this session: a file earns its existence when
you can say in one sentence what goes in it and what stays out; the reason roadmap, decisions and
log separate is that their read moments differ; a new **Phase 0 — Inventory** for work that
inherits material; the interview rules that make phases 0–4 checkable; an *artifacts must agree*
gate before Build; the `[NEEDS CLARIFICATION]` marker across all templates; and decisions carrying
an id and a status, never edited once accepted.

The first use of the marker was on this repository's own roadmap: the TY2025 filing deadline had
been written as a fact when it was an inference from a table, never confirmed.

Found while reorganizing: the registry `R1–R14` was entirely unverified on paper while
`docs/reference/Investigacion_Renta.md` had already closed nine of those rules against primary
sources — the research was done and the registry never learned about it. Five rules the engine
already implements were missing from the registry altogether and were added as `R15–R19`.

## 2026-08-10 — Discovery closed, constitution written

The discovery sessions on Claude.ai ended with the data-handling and product-behaviour decisions
now recorded in `docs/decisions.md` (A1–D3). The ten project principles were written to
`memory/constitution.md`. Session context was exported to `docs/context/CONTEXT.md` for transfer
into Claude Code — a temporary file, to be emptied into its destinations and deleted.

## 2026-07 → 2026-08 — Excel calculation engine built and validated

The deterministic engine was built in Excel for the employee profile and reproduces three real
returns to the peso (two filed, one draft pending professional review). These are the golden
tests. **M1 met** (zero difference on key lines) and **M3 met** (switching tax year touches only
the Parameters sheet). **M2 is not verified** and **M5 has not been run.**

Reconstructing each year from original source documents rather than from the previous year's
results surfaced several findings on already-filed returns, all tax-neutral, and one advance payment
figure that no reading of art. 807 reproduces. Both are with a professional.

Two regressions appeared while rebuilding the 2025 file by copying the previous one — an AFC cap
and a dependants cap broke silently. That is the origin of the golden-test rule in the
constitution.

## 2026-07-06 — Business model merged into the plan

A session on monetization produced the free → X → Y funnel, moved exógena parsing from P3 to P2,
and established the owner tags. `PLAN.md` went to v0.3.
