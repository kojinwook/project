package com.example.project.note.note;


import com.example.project.note.notebook.Notebook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public Note saveDefault() {
        Note note = new Note();
        note.setTitle("새로운 매치");
        note.setNotice("모든 레벨 \n" +
                "남자만 \n" +
                "6 vs 6 3파전 \n" +
                " 10 ~ 18 명 \n" +
                " 풋살화 / 운동화 \n" +
                " 매치 진행 방식 :"+  "\n" +
                "매치 규칙\n" +
                "모든 파울은 사이드라인에서 킥인\n" +
                "골키퍼에게 백패스 가능 손으로는 잡으면 안 돼요\n" +
                "사람을 향한 태클 금지\n" +
                "진행 방식\n" +
                "풋살화와 개인 음료만 준비하세요\n" +
                "매니저가 경기 진행을 도와드려요\n" +
                "골키퍼와 휴식을 공평하게 돌아가면서 해요\n" +
                "레벨 데이터를 기준으로 팀을 나눠요\n" +
                "친구끼리 와도 팀 실력이 맞지 않으면 다른 팀이 될 수 있어요\n" +
                " 환불 정책 : "+ "\n" +
                "신청 취소 시 환불 기준\n" +
                "매치 2일 전\t무료 취소\n" +
                "매치 1일 전\t80% 환급\n" +
                "당일 ~ 매치 시작 90분 전까지\t20% 환급\n" +
                "매치 시작 90분 이내\t환불 불가\n" +
                "취소 수수료 발생 시 사용된 포인트를 우선 차감 후 차액을 캐시로 지급 합니다.\n" +
                "그 외 취소 기준\n" +
                "결제 후 30분 이내에는 하루 1회에 한해 무료 취소가 가능합니다. (단, 매치 시작 90분 이내일 경우 불가)\n" +
                "쿠폰 신청자는 매치 시작 90분 전까지 취소 시 쿠폰이 반환됩니다.\n" +
                "결제 시 실 결제금액(쿠폰 제외)을 기준으로 위 규정에 따라 환급됩니다.\n" +
                "현장에서 매치가 취소되는 경우 참가비는 진행되지 않은 시간만큼 다음날 오전 환급됩니다.\n" +
                "매치 시작 90분 전까지 최소 인원이 모이지 않을 시 카카오톡 혹은 LMS으로 안내되며, 자동 전액 환불됩니다. (단, 공지 전 직접 취소하시는 경우 상단 일반 환불 규정대로 처리되니 유의하시길 바랍니다)\n" +
                "다음의 경우는 환불이 불가합니다.\n" +
                "구장, 날짜, 시간 등을 실수로 잘못 선택한 경우\n" +
                "부상, 취업 등 개인 사정으로 신청된 매치에 참여하지 못하는 경우\n" +
                "단체 혹은 지인과의 참가로 매치 취소 혹은 변경을 원하는 경우\n" +
                "황사/미세먼지로 인해 취소 혹은 변경을 요청하는 경우\n" +
                "단순 변심으로 취소 혹은 변경을 요청하는 경우\n" +
                "유의사항\n" +
                "무단 불참하거나 매치 시작 90분 이내에 취소하면 패널티를 받을 수 있습니다.\n" +
                "(참여가 어려울 경우, 환불이 불가능하더라도 원활한 매치 진행을 위해 나의 플랩에서 미리 취소해 주세요.)\n" +
                "변경 정책\n" +
                "변경은 취소와 동일한 환불 규정으로 적용됩니다.\n" +
                "변경은 상단 환불 정책 기준 100% 환불일 경우에만 가능하며, 규정 외 요청은 적용이 불가합니다.\n" +
                "우천 및 폭설 정책\n" +
                "기상청 날씨누리 예보에 따라 진행 여부 및 환불 가능 여부를 공지해드립니다.\n" +
                "- 공지 시점\n" +
                "오전(12시 이전) 매치 : 하루 전 22시 기준, 매치 진행 시간의 강수량 예보가 1mm 이상 시\n" +
                "오후(12시 이후) 매치 : 매치 시작 4시간 전 기준, 매치 진행 시간의 강수량 예보가 1mm 이상 시\n" +
                "- 공지 방법 : 카카오톡 또는 문자(본인 인증된 연락처)\n" +
                "‘강수 안내 알림톡’을 받고, 매치 시작 90분 전까지 취소하면 전액 환불됩니다.\n" +
                "갑작스러운 기상 악화로 1mm 이상 강수 예보가 발생하였으나 매치 3시간 전까지 알림톡이 발송되지 못한 경우, 아래와 같이 처리됩니다.\n" +
                "매치 시작 1시간 30분 ~ 3시간 전까지 홈페이지 우측 하단 채널톡으로 문의 시 확인 후 강수 안내 알림톡 발송 또는 전액 환불 처리\n" +
                "매치 시작까지 1시간 30분 미만 남은 경우 매치 확정 후 현장으로 출발한 참가자가 있을 수 있어 현장 방문 후 매니저에게 취소 의사 전달 시 전액 환불 처리 가능(익일 오전 일괄 환급)");
        note.setContent("날짜 : \n" +
                " 장소 : \n" +
                " 시간 : \n" +
                " 구장 정보 : \n ");
        note.setCreateDate(LocalDateTime.now());

        return noteRepository.save(note);
    }

    public Note getNote(Long id) {
        return noteRepository.findById(id).orElseThrow();
    }

    public List<Note> getNoteListByNotebook(Notebook targetNotebook) {
        return noteRepository.findByNotebook(targetNotebook);
    }

    public void save(Note note) {
        noteRepository.save(note);
    }

    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

    public List<Note> getSearchedNoteList(String keyword) {
        return noteRepository.findByTitleContaining(keyword);
    }

    public List<Note> getSortedListByCreateDate(Notebook targetNotebook) {
        return noteRepository.findByNotebookOrderByCreateDateDesc(targetNotebook);
    }

    public List<Note> getSortedListByTitle(Notebook targetNotebook) {
        return noteRepository.findByNotebookOrderByTitle(targetNotebook);
    }
}