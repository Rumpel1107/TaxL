# Tasks — Slice zero

> Phase 4. Execution order, not topic order. The number is the running order.
>
> Anything not known goes in as `[NEEDS CLARIFICATION: the question]`, never as a
> plausible guess. Nothing is written here until it was confirmed in conversation.

## What slice zero is

The end-to-end skeleton of the first slice, deployed on the server and served over HTTPS, with
no engine in it (D50, D58). It contains every layer the first slice will deploy — reverse proxy,
served interface, API, database — and none it will not: no engine, no form, no real screen.

What it shows is the minimum that proves the layers talk to each other: one page that asks the
API for its status, and the API answers after asking the database. When that page opens in a
browser with a lock, the whole path works.

Two things belong here because they are about deployment, not product:

- The service comes back on its own after the server reboots — the constraint every project in
  the portfolio inherits.
- Each layer has one automated test, so the slices below verify themselves and not only by eye.

Out of scope, on purpose: the CI of D49 — its value arrives with the invented reference case,
which is born with the engine. It stays roadmap item 7.

## Decisions this document rests on

| Id | In one line |
|---|---|
| D47 | Java 21 + Spring Boot, React + TypeScript, text in per-language files, a database |
| D50 | Slice zero comes before the engine |
| D58 | The server is a rented VPS; the site name lives only in the reverse proxy's configuration on the server |
| D59 | PostgreSQL |
| D60 | API, interface and database as containers in one Compose file; the reverse proxy stays native |
| D61 | Images are built on the server from a `git pull`; CI-built images come with the first users |
| D62 | One repository, `backend/` and `frontend/` side by side, Maven |

Rules from the method that shape the slices: a secret is only ever read from a file (the
database password appears in no Compose file, command line or log); the tag answers *what am I
running* (the status page shows the version); a measurement only sees what it was built to see
(the database check is verified in both directions).

## Slices

| # | Slice | Covers | How it is verified | Done |
|---|---|---|---|---|
| 1 | **The API builds and runs on the server.** `backend/` with Maven and Spring Boot, one endpoint that returns the running version, a Dockerfile, and `compose.yml` at the root with the API as its only service. Built and started on the VPS with Compose — no Java installed on the system (D61). `CONTRIBUTING.md` *Dev setup* gets how to build, run and test. | The build on 4 GB; D61, D62 | A Spring test on the endpoint, run inside the build. On the VPS: `docker compose up` and a local request returns the version. The build's peak memory and duration are recorded in the log — this is the slice that can invalidate D61 | ✅ 2026-09-15 |
| 2 | **The database is there and the API sees it.** PostgreSQL added to Compose with a named volume; the password read from a file the repository does not hold; the status endpoint reports whether the database answered, and becomes the container's health check. Docker alone only marks an unhealthy container, it does not restart it: this slice decides what restarts a process that is alive but not answering. Both points were raised by the slice-1 review and belong here. | D59; the secret rule | The status reports the database as reachable. Both directions: stop the database container and the status reports it as unreachable, without the API dying. Data written to the volume survives `compose down` and `up`. The password is absent from `compose.yml`, `docker inspect`, the process list and the logs | ✅ 2026-09-16, pending review |
| 3 | **The page exists and shows the status.** `frontend/` with React and TypeScript, one page that calls the status endpoint and shows the version and the database state, its user-facing text in per-language files from the first line (D47), served by its own container in Compose. Open for this slice: how the page reaches the API from the browser — same origin through a proxy, or CORS headers on the API (raised by the slice-1 review). | The interface layer; D47, D60 | One Playwright test (D54) that opens the page and asserts the version and the database state are shown. Reachable locally on the VPS through Compose | ⬜ |
| 4 | **Served over HTTPS under its name.** A site block in the server's reverse proxy configuration, proxying to the Compose services; the certificate obtained and renewed automatically; plain HTTP not served. The name appears in that file and nowhere else (D58). | AC22, first met here (plan.md) | From a machine outside the server: the page opens over HTTPS with a valid certificate; a plain HTTP request is redirected or refused; the repository contains no occurrence of the site name | ⬜ |
| 5 | **It comes back on its own.** The server is rebooted with nothing touched afterwards. | The portfolio's availability constraint | After the reboot, the page opens from outside with the lock, the status shows the database reachable, and `docker ps` shows the three containers up without anyone having run a command | ⬜ |

## Notes

- Slice 1 goes first because it is the one that can invalidate the others: if compiling Java
  inside a build on 4 GB with 2 GB of swap is too slow or fails, D61 is reopened before anything
  sits on top of it.
- Slice 4 depends on the DNS record for the name pointing at the server. That record is made by
  hand in the domain's panel before the slice starts; it is not part of the repository.
- Slice 5 is the one test the owner's previous server already failed once, with another service.
  It is not a formality.
- Nothing in these slices asks for Java or Node on the server itself: the images bring their own.
- The roadmap's item 7 (CI) and the `Dev setup` section overlap by design: slice 1 writes the
  section as far as building and running locally; item 7 adds the CI when there is a reference
  case to run.

---

**Exit gate**

- [x] Slices in execution order
- [x] Each slice states its verification
- [x] No slice leaves code that nothing calls
- [x] The phase documents were read against each other; contradictions resolved or recorded — `plan.md` (deployment row, AC22), the roadmap (items 2 and 7), the constitution, D47–D62
- [x] No `[NEEDS CLARIFICATION]` marker is left unresolved
- [x] Every claim here was confirmed in conversation before it was written down — 2026-09-13
