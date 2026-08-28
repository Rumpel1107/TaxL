# Log

> **What goes in this document:** what happened, dated, newest first. Append-only.
>
> Not here: why a decision was taken (`docs/decisions.md`), what is pending
> (`docs/roadmap.md`). No taxpayer figures — golden-test values stay in the local fixture,
> never in this repository (`memory/constitution.md`, principle 3).

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
