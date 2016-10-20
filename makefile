MAKEFLAGS := -j 4

SHELL := /bin/bash

USER := byuadmin

uppercase = $(shell tr '[a-z]' '[A-Z]' <<< $1)

CONTEST := LUCID2016
PROBLEMS := $(wildcard problems/*)
TEST_DIRS := $(wildcard problems/*/test/data)
UPLOADS_SRC := $(wildcard contest/uploads/*)

.PHONY: all
all: contest problems solutions uploads

.PHONY: contest
contest: target/LUCID2016.html

.PHONY: solutions
solutions: $(SOLUTION_DIRS:%=target/%.zip.upload)

.PHONY: problems
problems: $(PROBLEMS:%=target/%.upload)

.PHONY: uploads
uploads: $(UPLOADS_SRC:%=target/%)

.PHONY: clean
clean:
	rm -fr \
		problem-descriptions.pdf \
		problems/*/description.html \
		target

PROBLEM_DESCRIPTIONS_MD := $(wildcard problems/*/description.md)
PROBLEM_DESCRIPTIONS_HTML := $(PROBLEM_DESCRIPTIONS_MD:%.md=%.html)

problem-descriptions.pdf: $(PROBLEM_DESCRIPTIONS_HTML)
	wkhtmltopdf -g --print-media-type $^ $@

problems/%/description.html: problems/%/description.md
	ruby -rerb -rnet/http -e 'puts ERB.new(File.read "convert.html.erb").result' < $< > $@

target/contest.upload: contest/footer.html
	@mkdir -p $(@D)
	./spoj.py --user=$(USER) --password=$(PASSWORD) contest --footer-file=$< $(CONTEST)
	@> $@

target/contest/uploads/%: uploads/%
	@mkdir -p $(@D)
	./spoj.py --user=$(USER) --password=$(PASSWORD) upload --file=$< $(CONTEST) $(*:%.html=%)
	@> $@

target/problems/%/test/data.zip: 
	@mkdir -p $(@D)
	@if [ -z "$^" ]; then > $@; else zip --filesync -j $@ $^; fi

target/problems/%/test/data.zip.upload: problems/%/test/data.zip
	./spoj.py --user=$(USER) --password=$(PASSWORD) tests --zip-file=$< $(call uppercase,$*)
	@> $@

target/problems/%.upload: problems/%/description.html
	@mkdir -p $(@D)
	./spoj.py --user=$(USER) --password=$(PASSWORD) problem --body-file=$< $(call uppercase,$*)
	@> $@
