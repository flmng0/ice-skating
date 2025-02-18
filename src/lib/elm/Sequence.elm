port module Sequence exposing (main)


type alias Edge =
    Int


type alias Move =
    { name : String
    , entry : Edge
    , exit : Edge
    }


toMove : String -> Edge -> Edge -> Move
toMove name entry exit =
    { name = name, entry = entry, exit = exit }


main : Program () Model Msg
main =
    Platform.worker
        { init = init
        , update = update
        , subscriptions = subscriptions
        }


port sendSequence : List String -> Cmd msg


port generateNewSequence : (Int -> msg) -> Sub msg


type alias Model =
    ()


type Msg
    = Generate Int


generateSequence : Int -> List String
generateSequence n =
    List.map String.fromInt (List.range 1 n)


init : flags -> ( Model, Cmd Msg )
init _ =
    ( (), Cmd.none )


update : Msg -> Model -> ( Model, Cmd Msg )
update msg model =
    case msg of
        Generate count ->
            ( model, sendSequence (generateSequence count) )


subscriptions _ =
    generateNewSequence Generate
